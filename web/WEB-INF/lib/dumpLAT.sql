CREATE TABLE `adverts` (
  `id` int(11) NOT NULL,
  `title` varchar(150) NOT NULL,
  `description` varchar(2048) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `date_start` date NOT NULL,
  `date_end` date DEFAULT NULL,
  `simple_descritpion` varchar(255) DEFAULT NULL,
  `id_users` int(11) DEFAULT NULL,
  `id_category` int(11) DEFAULT NULL,
  `id_state` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `apply` (
  `id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `accepted` tinyint(1) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_adverts` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `parent_category` int(11) DEFAULT NULL,
  `type_category` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `friends` (
  `id_user` int(11) NOT NULL,
  `id_user1` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `groups` (
  `id` int(11) NOT NULL,
  `groups_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `loan` (
  `id` int(11) NOT NULL,
  `loan_code` varchar(255) NOT NULL,
  `state_loan_code` tinyint(1) NOT NULL,
  `return_code` varchar(255) NOT NULL,
  `state_return_code` tinyint(1) NOT NULL,
  `id_apply` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `opinion` (
  `id` int(11) NOT NULL,
  `mark` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `reporting` (
  `id` int(11) NOT NULL,
  `reason` varchar(255) NOT NULL,
  `more_information` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `reporting_advert` (
  `id` int(11) NOT NULL,
  `id_advert` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `reporting_user` (
  `id` int(11) NOT NULL,
  `id_users` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `state` (
  `id` int(11) NOT NULL,
  `state_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `firstname` varchar(20) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `email` varchar(60) NOT NULL,
  `nb_street` int(5) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `zipcode` char(5) DEFAULT NULL,
  `password` char(56) NOT NULL,
  `birthday` date DEFAULT NULL,
  `preferences` varchar(255) DEFAULT NULL,
  `school_domain` varchar(255) DEFAULT NULL,
  `education_formation` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `id_groups` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE `warning` (
  `id` int(11) NOT NULL,
  `reason` varchar(255) NOT NULL,
  `descritpion` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

ALTER TABLE `adverts`
ADD PRIMARY KEY (`id`), ADD KEY `FK_ADVERT` (`id_users`), ADD KEY `FK1_ADVERT` (`id_category`), ADD KEY `FK2_ADVERT` (`id_state`);

ALTER TABLE `apply`
ADD PRIMARY KEY (`id`), ADD KEY `FK_APPLY` (`id_user`), ADD KEY `FK1_APPLY` (`id_adverts`);

ALTER TABLE `category`
ADD PRIMARY KEY (`id`), ADD KEY `parent_category` (`parent_category`);

ALTER TABLE `friends`
ADD PRIMARY KEY (`id_user`,`id_user1`), ADD KEY `id_user1` (`id_user1`);

ALTER TABLE `groups`
ADD PRIMARY KEY (`id`);

ALTER TABLE `loan`
ADD PRIMARY KEY (`id`), ADD KEY `id_apply` (`id_apply`);

ALTER TABLE `opinion`
ADD PRIMARY KEY (`id`), ADD KEY `id_user` (`id_user`);

ALTER TABLE `reporting`
ADD PRIMARY KEY (`id`);

ALTER TABLE `reporting_advert`
ADD PRIMARY KEY (`id`), ADD KEY `id_advert` (`id_advert`);

ALTER TABLE `reporting_user`
ADD PRIMARY KEY (`id`), ADD KEY `id_users` (`id_users`);

ALTER TABLE `state`
ADD PRIMARY KEY (`id`);

ALTER TABLE `users`
ADD PRIMARY KEY (`id`), ADD KEY `id_groups` (`id_groups`);

ALTER TABLE `warning`
ADD PRIMARY KEY (`id`), ADD KEY `id_user` (`id_user`);

ALTER TABLE `adverts`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `apply`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `category`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `groups`
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

ALTER TABLE `state`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `users`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `warning`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `adverts`
ADD CONSTRAINT `FK_ADVERT` FOREIGN KEY (`id_users`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `FK1_ADVERT` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `FK2_ADVERT` FOREIGN KEY (`id_state`) REFERENCES `state` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `apply`
ADD CONSTRAINT `FK_APPLY` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `FK1_APPLY` FOREIGN KEY (`id_adverts`) REFERENCES `adverts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `category`
ADD CONSTRAINT `category_ibfk_1` FOREIGN KEY (`parent_category`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `friends`
ADD CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`id_user1`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `loan`
ADD CONSTRAINT `loan_ibfk_1` FOREIGN KEY (`id_apply`) REFERENCES `apply` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `opinion`
ADD CONSTRAINT `opinion_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `reporting_advert`
ADD CONSTRAINT `reporting_advert_ibfk_1` FOREIGN KEY (`id`) REFERENCES `reporting` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `reporting_advert_ibfk_2` FOREIGN KEY (`id_advert`) REFERENCES `adverts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `reporting_user`
ADD CONSTRAINT `reporting_user_ibfk_1` FOREIGN KEY (`id`) REFERENCES `reporting` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `reporting_user_ibfk_2` FOREIGN KEY (`id_users`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `users`
ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`id_groups`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `warning`
ADD CONSTRAINT `warning_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
