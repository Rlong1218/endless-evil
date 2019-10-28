### Database

```java
package com.rlong1218.endlessevil.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import com.rlong1218.endlessevil.entity.Character;
import com.rlong1218.endlessevil.entity.Game;
import com.rlong1218.endlessevil.model.CharacterDao;
import com.rlong1218.endlessevil.model.GameDao;
import java.util.Date;

@Database(
    entities = {Character.class, Game.class},
    version = 1, exportSchema = true
)
@TypeConverters(EndlessEvilDatabase.Converters.class)
public abstract class EndlessEvilDatabase extends RoomDatabase {

  protected EndlessEvilDatabase() {}

  private static Application applicationContext;

  public static void setApplicationContext(Application applicationContext){
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
              (applicationContext, EndlessEvilDatabase.class, "endless_evil_db").build();
    }

  }

  public static class Converters {

    @TypeConverter
    public Long dateToLong(Date date) {return (date != null) ? date.getTime() : null;}

    @TypeConverter
    public Date longToDate(Long milliseconds) {
      return (milliseconds != null) ? new Date(milliseconds) : null;
    }

  }

}

```