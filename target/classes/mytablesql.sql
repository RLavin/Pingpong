CREATE SCHEMA  pingpong;

CREATE TABLE pingpong.player
(
    pla_id INTEGER PRIMARY KEY NOT NULL,
    pla_name VARCHAR(255),
    pla_nickname VARCHAR(255),
    pla_wins INTEGER,
    pla_losses INTEGER
);

CREATE TABLE pingpong.match
(
    mat_id INTEGER PRIMARY KEY NOT NULL,
    mat_playerOne VARCHAR(255),
    mat_playerTwo VARCHAR(255),
    mat_playerOneScore INTEGER,
    mat_playerTwoScore INTEGER
);

CREATE SEQUENCE pingpong.PLAYER_SEQ;

CREATE SEQUENCE pingpong.MATCH_SEQ;


