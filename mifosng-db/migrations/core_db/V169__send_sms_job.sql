INSERT INTO `job` (`name`, `display_name`, `cron_expression`, `create_time`, `task_priority`) VALUES ('Send out pending SMS', 'Send pending SMS to Gateway', '0 0 0 1/1 * ? *', now(), 5);
