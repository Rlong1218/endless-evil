## Data Definition Language (DDL) for data model 

```sql
CREATE TABLE IF NOT EXISTS `Character`
(
    `character_id`    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `created`         INTEGER                           NOT NULL,
    `health_upgrades` INTEGER                           NOT NULL,
    `damage_upgrades` INTEGER                           NOT NULL,
    `total_kills`     INTEGER                           NOT NULL,
    `high_score`      INTEGER                           NOT NULL
);

CREATE INDEX IF NOT EXISTS `index_Character_created` ON `Character` (`created`);

CREATE TABLE IF NOT EXISTS `Game`
(
    `game_id`       INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `created`       INTEGER                           NOT NULL,
    `character_id`  INTEGER                           NOT NULL,
    `points_earned` INTEGER                           NOT NULL,
    `levels_beaten` INTEGER                           NOT NULL,
    FOREIGN KEY (`character_id`) REFERENCES `Character` (`character_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE INDEX IF NOT EXISTS `index_Game_created` ON `Game` (`created`);

CREATE INDEX IF NOT EXISTS `index_Game_character_id` ON `Game` (`character_id`);

```