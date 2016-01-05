CREATE TABLE `advert` (
  `id` int(11) NOT NULL,
  `title` varchar(150) NOT NULL,
  `description` varchar(2048) DEFAULT NULL,
  `short_description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `date_start` date NOT NULL,
  `date_end` date DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `is_publish` BOOLEAN DEFAULT NULL,
  `is_suspend` BOOLEAN DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `apply` (
  `id` int(11) NOT NULL,
  `date_start` date NOT NULL,
  `date_end` date DEFAULT NULL,
  `accepted` tinyint(1) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `advert_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `parent_category` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `friends` (
  `user_id` int(11) NOT NULL,
  `user_id1` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `group` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `loan` (
  `id` int(11) NOT NULL,
  `code` varchar(255) NOT NULL,
  `state_code` tinyint(1) NOT NULL,
  `return_code` varchar(255) NOT NULL,
  `state_return_code` tinyint(1) NOT NULL,
  `apply_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `opinion` (
  `id` int(11) NOT NULL,
  `mark` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `reporting` (
  `id` int(11) NOT NULL,
  `reason` varchar(255) NOT NULL,
  `more_information` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `reporting_advert` (
  `id` int(11) NOT NULL,
  `advert_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `reporting_user` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `lastname` varchar(20) DEFAULT NULL,
  `firstname` varchar(20) DEFAULT NULL,
  `nickname` varchar(20) NOT NULL,
  `email` varchar(60) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `zipcode` char(5) DEFAULT NULL,
  `password` char(56) NOT NULL,
  `birthday` date DEFAULT NULL,
  `preferences` varchar(255) DEFAULT NULL,
  `school_domain` varchar(255) DEFAULT NULL,
  `education_formation` varchar(255) DEFAULT NULL,
  `is_suspend` BOOLEAN DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `warning` (
  `id` int(11) NOT NULL,
  `reason` varchar(255) NOT NULL,
  `descritpion` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

ALTER TABLE `advert`
ADD PRIMARY KEY (`id`), ADD KEY `FK_ADVERT` (`user_id`), ADD KEY `FK1_ADVERT` (`category_id`);

ALTER TABLE `apply`
ADD PRIMARY KEY (`id`), ADD KEY `FK_APPLY` (`user_id`), ADD KEY `FK1_APPLY` (`advert_id`);

ALTER TABLE `category`
ADD PRIMARY KEY (`id`), ADD KEY `parent_category` (`parent_category`);

ALTER TABLE `friends`
ADD PRIMARY KEY (`user_id`,`user_id1`), ADD KEY `user_id1` (`user_id1`);

ALTER TABLE `group`
ADD PRIMARY KEY (`id`);

ALTER TABLE `loan`
ADD PRIMARY KEY (`id`), ADD KEY `apply_id` (`apply_id`);

ALTER TABLE `opinion`
ADD PRIMARY KEY (`id`), ADD KEY `user_id` (`user_id`);

ALTER TABLE `reporting`
ADD PRIMARY KEY (`id`);

ALTER TABLE `reporting_advert`
ADD PRIMARY KEY (`id`), ADD KEY `advert_id` (`advert_id`);

ALTER TABLE `reporting_user`
ADD PRIMARY KEY (`id`), ADD KEY `user_id` (`user_id`);

ALTER TABLE `user`
ADD PRIMARY KEY (`id`), ADD KEY `group_id` (`group_id`);

ALTER TABLE `warning`
ADD PRIMARY KEY (`id`), ADD KEY `user_id` (`user_id`);

ALTER TABLE `advert`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `apply`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `category`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `group`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `loan`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `opinion`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `reporting`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `reporting_advert`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `reporting_user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `warning`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `advert`
ADD CONSTRAINT `FK_ADVERT` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `FK1_ADVERT` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `apply`
ADD CONSTRAINT `FK_APPLY` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `FK1_APPLY` FOREIGN KEY (`advert_id`) REFERENCES `advert` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `category`
ADD CONSTRAINT `category_ibfk_1` FOREIGN KEY (`parent_category`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `friends`
ADD CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`user_id1`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `loan`
ADD CONSTRAINT `loan_ibfk_1` FOREIGN KEY (`apply_id`) REFERENCES `apply` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `opinion`
ADD CONSTRAINT `opinion_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `reporting_advert`
ADD CONSTRAINT `reporting_advert_ibfk_2` FOREIGN KEY (`advert_id`) REFERENCES `advert` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `reporting_user`
ADD CONSTRAINT `reporting_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `user`
ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `warning`
ADD CONSTRAINT `warning_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
