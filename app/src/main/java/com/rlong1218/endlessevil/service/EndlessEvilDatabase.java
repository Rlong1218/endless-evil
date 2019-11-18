package com.rlong1218.endlessevil.service;

import android.app.Application;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.rlong1218.endlessevil.model.entity.Character;
import com.rlong1218.endlessevil.model.entity.Game;
import com.rlong1218.endlessevil.model.Dao.CharacterDao;
import com.rlong1218.endlessevil.model.Dao.GameDao;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@Database(
    entities = {Character.class, Game.class},
    version = 1, exportSchema = true
)
@TypeConverters(EndlessEvilDatabase.Converters.class)
public abstract class EndlessEvilDatabase extends RoomDatabase {

  protected EndlessEvilDatabase() {
  }

  private static Application applicationContext;

  public static void setApplicationContext(Application applicationContext) {
    EndlessEvilDatabase.applicationContext = applicationContext;
  }

  public static EndlessEvilDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract CharacterDao getCharacterDao();

  public abstract GameDao getGameDao();

  private static class InstanceHolder {

    private static final EndlessEvilDatabase INSTANCE;

    static {
      INSTANCE =
          Room.databaseBuilder
              (applicationContext, EndlessEvilDatabase.class, "endless_evil_db")
              .addCallback(new PrePopulation())
              .build();
    }

  }

  public static class Converters {

    @TypeConverter
    public Long dateToLong(Date date) {
      return (date != null) ? date.getTime() : null;
    }

    @TypeConverter
    public Date longToDate(Long milliseconds) {
      return (milliseconds != null) ? new Date(milliseconds) : null;
    }

  }

  private static class PrePopulation extends Callback {

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      new Thread(() -> {
        Resources res = applicationContext.getResources();
        int id = res.getIdentifier("roster", "raw", applicationContext.getPackageName());
        try (
            InputStream input = res.openRawResource(id);
            CSVParser parser = CSVParser.parse(input, Charset.defaultCharset(),
                CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withIgnoreSurroundingSpaces());
        ) {
          List<Character> characters = new LinkedList<>();
          for (CSVRecord record : parser) {
            Character character = new Character();
            character.setName(record.get("name"));
            int drawableId = res.getIdentifier(record.get("image"), "drawable", applicationContext.getPackageName());
             character.setImage(drawableId);
             drawableId = res.getIdentifier(record.get("icon"), "drawable", applicationContext.getPackageName());
             character.setIcon(drawableId);
            character.setBaselineHealth(Integer.parseInt(record.get("baseline_health")));
            character.setBaselineDamage(Integer.parseInt(record.get("baseline_damage")));
            characters.add(character);
          }
          EndlessEvilDatabase.getInstance().getCharacterDao().insert(characters);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }).start();
    }
  }
}
