DROP TABLE IF EXISTS subtask;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS user_task;
DROP TABLE IF EXISTS user_project;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS project;
-----------------

CREATE TABLE user (
        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50) NOT NULL
);

INSERT INTO user (name) VALUES ('JOHN'),
                               ('PETER'),
                               ('SARAH'),
                               ('OLIVIA');
-----------------

CREATE TABLE project (
        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(200) NOT NULL
);

INSERT INTO project (name) VALUES ('PROJECT_1'),
                                  ('PROJECT_2'),
                                  ('PROJECT_3');
-----------------

CREATE TABLE user_project (
         user_id BIGINT NOT NULL,
         project_id BIGINT NOT NULL
);

INSERT INTO user_project (project_id, user_id) VALUES (1, 1),
                                                      (2, 1),
                                                      (1, 2),
                                                      (3, 2),
                                                      (2, 2),
                                                      (2, 3),
                                                      (3, 1);

ALTER TABLE user_project ADD CONSTRAINT user_project_unique UNIQUE (project_id, user_id);
-----------------

CREATE TABLE task (
        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(250) NOT NULL,
        project_id BIGINT
);

INSERT INTO task (name, project_id) VALUES ('TASK_1', 2),
                                           ('TASK_2', 3),
                                           ('TASK_3', 3),
                                           ('TASK_4', 2),
                                           ('TASK_5', 1),
                                           ('TASK_6', 2),
                                           ('TASK_7', 1);
-----------------

CREATE TABLE user_task (
       user_id BIGINT NOT NULL,
       task_id BIGINT NOT NULL
);

INSERT INTO user_task (user_id, task_id) VALUES (1, 1),
                                                (2, 3),
                                                (1, 4),
                                                (3, 4),
                                                (2, 6),
                                                (3, 7);

ALTER TABLE user_task ADD CONSTRAINT user_task_unique UNIQUE (user_id, task_id);
-----------------

CREATE TABLE subtask (
       id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
       name VARCHAR(200) NOT NULL,
       task_id BIGINT
);

INSERT INTO subtask (name, task_id) values ('SUBTASK_1', 1),
                                           ('SUBTASK_2', 3),
                                           ('SUBTASK_3', 5);