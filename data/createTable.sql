CREATE TABLE `file_info` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;