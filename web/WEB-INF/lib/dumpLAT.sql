--
-- Base de données :  `lat`
--

-- --------------------------------------------------------

--
-- Structure de la table `ADVERTS`
--

CREATE TABLE `ADVERTS` (
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

-- --------------------------------------------------------

--
-- Structure de la table `APPLY`
--

CREATE TABLE `APPLY` (
`id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `accepted` tinyint(1) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_adverts` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `CATEGORY`
--

CREATE TABLE `CATEGORY` (
`id` int(11) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `parent_category` int(11) DEFAULT NULL,
  `type_category` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `FRIENDS`
--

CREATE TABLE `FRIENDS` (
  `id_user` int(11) NOT NULL,
  `id_user1` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `GROUPS`
--

CREATE TABLE `GROUPS` (
`id` int(11) NOT NULL,
  `groups_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `LOAN`
--

CREATE TABLE `LOAN` (
`id` int(11) NOT NULL,
  `loan_code` varchar(255) NOT NULL,
  `state_loan_code` tinyint(1) NOT NULL,
  `return_code` varchar(255) NOT NULL,
  `state_return_code` tinyint(1) NOT NULL,
  `id_apply` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `OPINION`
--

CREATE TABLE `OPINION` (
`id` int(11) NOT NULL,
  `mark` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `REPORTING`
--

CREATE TABLE `REPORTING` (
`id` int(11) NOT NULL,
  `reason` varchar(255) NOT NULL,
  `more_information` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `REPORTING_ADVERT`
--

CREATE TABLE `REPORTING_ADVERT` (
`id` int(11) NOT NULL,
  `id_advert` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `REPORTING_USER`
--

CREATE TABLE `REPORTING_USER` (
`id` int(11) NOT NULL,
  `id_users` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `STATE`
--

CREATE TABLE `STATE` (
`id` int(11) NOT NULL,
  `state_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `USERS`
--

CREATE TABLE `USERS` (
`id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `nick_name` varchar(20) DEFAULT NULL,
  `email` varchar(60) NOT NULL,
  `nb_street` int(5) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `zipcode` char(5) DEFAULT NULL,
  `password` char(56) NOT NULL,
  `birth_day` date DEFAULT NULL,
  `preferences` varchar(255) DEFAULT NULL,
  `school_domain` varchar(255) DEFAULT NULL,
  `education_formation` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `id_groups` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `WARNING`
--

CREATE TABLE `WARNING` (
`id` int(11) NOT NULL,
  `reason` varchar(255) NOT NULL,
  `descritpion` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `ADVERTS`
--
ALTER TABLE `ADVERTS`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_ADVERT` (`id_users`), ADD KEY `FK1_ADVERT` (`id_category`), ADD KEY `FK2_ADVERT` (`id_state`);

--
-- Index pour la table `APPLY`
--
ALTER TABLE `APPLY`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_APPLY` (`id_user`), ADD KEY `FK1_APPLY` (`id_adverts`);

--
-- Index pour la table `CATEGORY`
--
ALTER TABLE `CATEGORY`
 ADD PRIMARY KEY (`id`), ADD KEY `parent_category` (`parent_category`);

--
-- Index pour la table `FRIENDS`
--
ALTER TABLE `FRIENDS`
 ADD PRIMARY KEY (`id_user`,`id_user1`), ADD KEY `id_user1` (`id_user1`);

--
-- Index pour la table `GROUPS`
--
ALTER TABLE `GROUPS`
 ADD PRIMARY KEY (`id`);

--
-- Index pour la table `LOAN`
--
ALTER TABLE `LOAN`
 ADD PRIMARY KEY (`id`), ADD KEY `id_apply` (`id_apply`);

--
-- Index pour la table `OPINION`
--
ALTER TABLE `OPINION`
 ADD PRIMARY KEY (`id`), ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `REPORTING`
--
ALTER TABLE `REPORTING`
 ADD PRIMARY KEY (`id`);

--
-- Index pour la table `REPORTING_ADVERT`
--
ALTER TABLE `REPORTING_ADVERT`
 ADD PRIMARY KEY (`id`), ADD KEY `id_advert` (`id_advert`);

--
-- Index pour la table `REPORTING_USER`
--
ALTER TABLE `REPORTING_USER`
 ADD PRIMARY KEY (`id`), ADD KEY `id_users` (`id_users`);

--
-- Index pour la table `STATE`
--
ALTER TABLE `STATE`
 ADD PRIMARY KEY (`id`);

--
-- Index pour la table `USERS`
--
ALTER TABLE `USERS`
 ADD PRIMARY KEY (`id`), ADD KEY `id_groups` (`id_groups`);

--
-- Index pour la table `WARNING`
--
ALTER TABLE `WARNING`
 ADD PRIMARY KEY (`id`), ADD KEY `id_user` (`id_user`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `ADVERTS`
--
ALTER TABLE `ADVERTS`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `APPLY`
--
ALTER TABLE `APPLY`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `CATEGORY`
--
ALTER TABLE `CATEGORY`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `GROUPS`
--
ALTER TABLE `GROUPS`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `LOAN`
--
ALTER TABLE `LOAN`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `OPINION`
--
ALTER TABLE `OPINION`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `REPORTING`
--
ALTER TABLE `REPORTING`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `REPORTING_ADVERT`
--
ALTER TABLE `REPORTING_ADVERT`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `REPORTING_USER`
--
ALTER TABLE `REPORTING_USER`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `STATE`
--
ALTER TABLE `STATE`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `USERS`
--
ALTER TABLE `USERS`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `WARNING`
--
ALTER TABLE `WARNING`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `ADVERTS`
--
ALTER TABLE `ADVERTS`
ADD CONSTRAINT `FK_ADVERT` FOREIGN KEY (`id_users`) REFERENCES `USERS` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `FK1_ADVERT` FOREIGN KEY (`id_category`) REFERENCES `CATEGORY` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `FK2_ADVERT` FOREIGN KEY (`id_state`) REFERENCES `STATE` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `APPLY`
--
ALTER TABLE `APPLY`
ADD CONSTRAINT `FK_APPLY` FOREIGN KEY (`id_user`) REFERENCES `USERS` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `FK1_APPLY` FOREIGN KEY (`id_adverts`) REFERENCES `ADVERTS` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `CATEGORY`
--
ALTER TABLE `CATEGORY`
ADD CONSTRAINT `category_ibfk_1` FOREIGN KEY (`parent_category`) REFERENCES `CATEGORY` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `FRIENDS`
--
ALTER TABLE `FRIENDS`
ADD CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `USERS` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`id_user1`) REFERENCES `USERS` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `LOAN`
--
ALTER TABLE `LOAN`
ADD CONSTRAINT `loan_ibfk_1` FOREIGN KEY (`id_apply`) REFERENCES `APPLY` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `OPINION`
--
ALTER TABLE `OPINION`
ADD CONSTRAINT `opinion_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `USERS` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `REPORTING_ADVERT`
--
ALTER TABLE `REPORTING_ADVERT`
ADD CONSTRAINT `reporting_advert_ibfk_1` FOREIGN KEY (`id`) REFERENCES `REPORTING` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `reporting_advert_ibfk_2` FOREIGN KEY (`id_advert`) REFERENCES `ADVERTS` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `REPORTING_USER`
--
ALTER TABLE `REPORTING_USER`
ADD CONSTRAINT `reporting_user_ibfk_1` FOREIGN KEY (`id`) REFERENCES `REPORTING` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `reporting_user_ibfk_2` FOREIGN KEY (`id_users`) REFERENCES `USERS` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `USERS`
--
ALTER TABLE `USERS`
ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`id_groups`) REFERENCES `GROUPS` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `WARNING`
--
ALTER TABLE `WARNING`
ADD CONSTRAINT `warning_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `USERS` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
