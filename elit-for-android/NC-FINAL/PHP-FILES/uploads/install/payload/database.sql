-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2+deb7u1
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Фев 18 2015 г., 14:11
-- Версия сервера: 5.5.41
-- Версия PHP: 5.4.36-0+deb7u3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `club76`
--

-- --------------------------------------------------------

--
-- Структура таблицы `76_about`
--

CREATE TABLE IF NOT EXISTS `76_about` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `description` text CHARACTER SET utf8 NOT NULL,
  `adres` text CHARACTER SET utf8 NOT NULL,
  `phone` text CHARACTER SET utf8 NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Дамп данных таблицы `76_about`
--

INSERT INTO `76_about` (`id`, `name`, `description`, `adres`, `phone`, `status`, `added`) VALUES
(1, 'Amnesia ibiza', 'The house where Amnesia now stands was built at the end of the XVIII century. Five generations of the Planells family lived within its thick whitewashed walls. They cultivated the land and built a mill out of stone. During the first half of the X century the poor that came to beg here received bread, cheese and dried figs.\n \nThe first foreign visitors arrived in Ibiza towards 1950. They were drawn to the island by its mysterious legends and rumors of its breathtaking untouched nature. In 1954 people like Aristoteles Onassis or Rainier of Monaco were startled by the beauty of the Pitiusan coves. Ibiza discovered tourism and began to prosper and grow.', 'Carretera Ibiza a San Antonio Km5 San Rafael 07816 (Ibiza) España', '34971198041', 0, '2014-11-01 02:29:36');

-- --------------------------------------------------------

--
-- Структура таблицы `76_djs`
--

CREATE TABLE IF NOT EXISTS `76_djs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 NOT NULL,
  `description` text CHARACTER SET utf8 NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Дамп данных таблицы `76_djs`
--

INSERT INTO `76_djs` (`id`, `title`, `description`, `status`, `added`) VALUES
(9, 'Fatboy Slim', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent sit amet dui ac ipsum imperdiet sagittis eget eu sapien. Nam cursus odio quis nunc fermentum, sit amet tincidunt nulla scelerisque. Ut nec erat lobortis, luctus felis et, congue lacus. Phasellus quis accumsan mauris, vitae dapibus ligula. Pellentesque sodales venenatis nulla, placerat ullamcorper lectus gravida ac. Mauris sollicitudin quis elit a volutpat. Mauris mattis mi sit amet convallis suscipit. Donec quis orci viverra, ornare magna vel, fringilla neque. Mauris nec leo odio. Nullam feugiat purus id egestas tristique. Donec fermentum, nunc at mattis venenatis, nulla justo venenatis eros, ut maximus nisl libero eget elit.', 0, '2014-11-07 03:54:02'),
(10, 'David Guetta', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent sit amet dui ac ipsum imperdiet sagittis eget eu sapien. Nam cursus odio quis nunc fermentum, sit amet tincidunt nulla scelerisque. Ut nec erat lobortis, luctus felis et, congue lacus. Phasellus quis accumsan mauris, vitae dapibus ligula. Pellentesque sodales venenatis nulla, placerat ullamcorper lectus gravida ac. Mauris sollicitudin quis elit a volutpat. Mauris mattis mi sit amet convallis suscipit. Donec quis orci viverra, ornare magna vel, fringilla neque. Mauris nec leo odio. Nullam feugiat purus id egestas tristique. Donec fermentum, nunc at mattis venenatis, nulla justo venenatis eros, ut maximus nisl libero eget elit.', 0, '2014-11-07 03:55:36'),
(11, 'Carl Cox', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent sit amet dui ac ipsum imperdiet sagittis eget eu sapien. Nam cursus odio quis nunc fermentum, sit amet tincidunt nulla scelerisque. Ut nec erat lobortis, luctus felis et, congue lacus. Phasellus quis accumsan mauris, vitae dapibus ligula. Pellentesque sodales venenatis nulla, placerat ullamcorper lectus gravida ac. Mauris sollicitudin quis elit a volutpat. Mauris mattis mi sit amet convallis suscipit. Donec quis orci viverra, ornare magna vel, fringilla neque. Mauris nec leo odio. Nullam feugiat purus id egestas tristique. Donec fermentum, nunc at mattis venenatis, nulla justo venenatis eros, ut maximus nisl libero eget elit.', 0, '2014-11-07 04:21:57');

-- --------------------------------------------------------

--
-- Структура таблицы `76_dtoken`
--

CREATE TABLE IF NOT EXISTS `76_dtoken` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tokenid` varchar(255) CHARACTER SET utf8 NOT NULL,
  `added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=77 ;

--
-- Дамп данных таблицы `76_dtoken`
--

INSERT INTO `76_dtoken` (`id`, `tokenid`, `added`) VALUES
(75, '70bbcc303addf4cd2e606daba782360808f8d4d0da87fd52a0f812a11037c0c8', '2015-02-09 13:21:15'),
(76, '0f5292993bbbe70a56728d6962eb1391dc9931294c20243320f1aee14c88c1d8', '2015-02-09 22:52:51');

-- --------------------------------------------------------

--
-- Структура таблицы `76_event`
--

CREATE TABLE IF NOT EXISTS `76_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 NOT NULL,
  `description` text CHARACTER SET utf8 NOT NULL,
  `datatime` varchar(255) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Дамп данных таблицы `76_event`
--

INSERT INTO `76_event` (`id`, `title`, `description`, `datatime`, `status`, `added`) VALUES
(12, 'Event One', '"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '2014/11/05', 0, '2014-11-04 19:17:04'),
(13, 'Event Two', '"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '2014/11/13', 0, '2014-11-04 19:21:02'),
(14, 'Big party event Dj Suchov and Ms Starr', '"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '2014/11/20', 0, '2014-11-04 20:13:12');

-- --------------------------------------------------------

--
-- Структура таблицы `76_images`
--

CREATE TABLE IF NOT EXISTS `76_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `type` varchar(50) CHARACTER SET utf8 NOT NULL,
  `path` text CHARACTER SET utf8 NOT NULL,
  `width` text CHARACTER SET utf8 NOT NULL,
  `height` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=372 ;

--
-- Дамп данных таблицы `76_images`
--

INSERT INTO `76_images` (`id`, `item_id`, `type`, `path`, `width`, `height`) VALUES
(302, 16, 'feed', '14143821387DPU_wbyIwc.jpg', '1106', '1024'),
(304, 17, 'news', '14143823921mY0E1TTQJk.jpg', '604', '405'),
(305, 18, 'news', '14143824821mY0E1TTQJk.jpg', '604', '405'),
(306, 19, 'news', '14143825127DPU_wbyIwc.jpg', '1106', '1024'),
(307, 19, 'news', '14143825221mY0E1TTQJk.jpg', '604', '405'),
(308, 20, 'news', '1414383709089hHlf0kIo.jpg', '1280', '891'),
(309, 21, 'news', '1414396225iEBBmhm6kd0.jpg', '1280', '853'),
(310, 22, 'news', '14144051041.mp3', '', ''),
(311, 23, 'news', '1414440891mhu-dj.jpg', '824', '420'),
(312, 24, 'news', '1414441527dj-party.jpg', '600', '398'),
(316, 10, 'mixes', '14144486351.mp3', '', ''),
(317, 10, 'news', '1414448658Градусы_-_Научится_бы_не_париться._._.(OST_Универ_._Новая_общага)_.mp3', '', ''),
(318, 10, 'news', '1414448703U2_-_Beautiful_Day.mp3', '', ''),
(319, 11, 'mixes', '14144501181.mp3', '', ''),
(320, 11, 'news', '14144501661.mp3', '', ''),
(321, 12, 'mixes', '1414450811Groove_Armada_-_Get_Down_(Henrik_B_Remix)____tmp_h_(vkontakte.ru_musmanias)_.mp3', '', ''),
(323, 8, 'dj', '1414534687hd-trees-in-sunlight-wallpapers-free-download-amazing-high-definition-wallpapers-of-trees.jpg', '2560', '1600'),
(324, 13, 'mixes', '1414535972Daft_Punk_-_Faster_Stronger_(No_Hopes_Remix).mp3', '', ''),
(325, 8, 'news', '1414807131happy-fucking-birthday1.jpg', '250', '353'),
(326, 0, 'news', '1414807162фото.JPG', '1280', '960'),
(327, 7, 'news', '1414807231344444444.jpg', '1999', '1266'),
(328, 7, 'event', '1414807289344444444.jpg', '1999', '1266'),
(329, 8, 'event', '14148073865uBfQTpMhmc.jpg', '682', '1024'),
(334, 10, 'event', '1415060231455555.jpeg', '1200', '1764'),
(335, 11, 'event', '1415127790event1.jpg', '560', '817'),
(336, 12, 'event', '1415128624event1.jpg', '560', '817'),
(337, 13, 'event', '1415128862event2.jpg', '560', '817'),
(338, 14, 'event', '1415131992event3.jpg', '560', '817'),
(339, 15, 'event', '1415298214colorful_2.jpg', '640', '1136'),
(340, 9, 'dj', '1415332442fbs.jpg', '800', '800'),
(341, 10, 'dj', '1415332536dvgt.jpg', '800', '800'),
(342, 11, 'dj', '1415334117ccx.jpg', '800', '800'),
(343, 25, 'news', '1415398494dillon-francis-press-photo-2014-billboard-650.jpg', '650', '430'),
(344, 26, 'news', '1415398672fifth-harmony-all-about-that-bass-party-2014-billboard-650x430.jpg', '650', '430'),
(348, 8, 'photo', '14155025042t9MyGboxCU.jpg', '900', '596'),
(349, 8, 'photo', '1415502513wh-p9sGAZnQ.jpg', '596', '900'),
(350, 8, 'photo', '1415502526z-3C3SVnNeA.jpg', '900', '596'),
(351, 8, 'photo', '1415502535ZWpdlzRfVi0.jpg', '596', '900'),
(353, 9, 'photo', '1415505479BgiEr6dSlVE.jpg', '1280', '853'),
(354, 9, 'photo', '1415505488DgU9lWRZ9v8.jpg', '1280', '853'),
(355, 9, 'photo', '1415505497hJf9S8B2L7E.jpg', '1280', '853'),
(356, 9, 'photo', '1415505507y0bTMavk2hs.jpg', '1280', '853'),
(357, 9, 'photo', '14155055410h2xP2ZlQnA.jpg', '682', '1024'),
(358, 10, 'photo', '1415505904Q20-XK-YLXE.jpg', '604', '401'),
(359, 10, 'photo', '14155059158el7LoUKb8w.jpg', '401', '604'),
(360, 10, 'photo', '1415505924j6SORfVbRvk.jpg', '401', '604'),
(361, 10, 'photo', '1415505935mencW1obLDs.jpg', '604', '401'),
(362, 10, 'photo', '1415505948oPFHgJbiL8A.jpg', '401', '604'),
(363, 10, 'photo', '1415505957PfJ-Y5pX3-A.jpg', '604', '401'),
(364, 27, 'news', '1415563983344444444.jpg', '1999', '1266'),
(366, 11, 'photo', '14230437840_1726_f43a46fe_orig.jpg', '3264', '2448'),
(367, 11, 'photo', '14230438000_174e_96e3a69_orig.jpg', '2448', '3264'),
(371, 1, 'setting', '1424180815logoze1.png', '500', '143');

-- --------------------------------------------------------

--
-- Структура таблицы `76_mix`
--

CREATE TABLE IF NOT EXISTS `76_mix` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 NOT NULL,
  `description` text CHARACTER SET utf8 NOT NULL,
  `fileurl` varchar(255) CHARACTER SET utf8 NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=69 ;

--
-- Дамп данных таблицы `76_mix`
--

INSERT INTO `76_mix` (`id`, `title`, `description`, `fileurl`, `status`, `added`) VALUES
(62, 'Youan - When You''re Around (Original Mix)', 'Youan - When You''re Around (Original Mix)', 'Youan_-_When_Youre_Around_(Original_Mix)_www.mixupload_.org_.mp3', 0, '2015-02-05 09:27:05'),
(64, 'Avicii - Hey Brother (By) ', 'Avicii - Hey Brother (By) ', 'Avicii_-_Hey_Brother_(By)_www.mixupload_.com_.mp3', 0, '2015-02-05 09:48:31'),
(65, 'Fedde Le Grand & D-wayne & Leon Bolier vs. Eddie Thoneick & Abel Ramos - Love Will Maetum (Y-COX Mashup) ', 'Fedde Le Grand & D-wayne & Leon Bolier vs. Eddie Thoneick & Abel Ramos - Love Will Maetum (Y-COX Mashup) ', 'Fedde_Le_Grand_D-wayne_Leon_Bolier_vs._Eddie_Thoneick_Abel_Ramos_-_Love_Will_Maetum_(Y-COX_Mashup)_www_.mixupload_.com_.mp3', 0, '2015-02-05 09:51:26'),
(66, 'The Killers feat. Fomichev - Somebody Told Me (Bma Project Remix) ', 'The Killers feat. Fomichev - Somebody Told Me (Bma Project Remix) ', 'The_Killers_feat._Fomichev_-_Somebody_Told_Me_(Bma_Project_Remix)_www_.mixupload_.com_.mp3', 0, '2015-02-05 09:57:36'),
(67, 'armin', 'armin', '001._Armin_Van_Buuren_-_This_Is_What_It_Feels_Like_(Feat_._Trevor_Guthrie)_.mp3', 0, '2015-02-12 21:51:02'),
(68, 'test mix 6', '', 'Fergie_-_L.A_._Love_(DJ_MaXxX_Mash-Up)_www_.mixupload_.com_.mp3', 0, '2015-02-17 12:16:18');

-- --------------------------------------------------------

--
-- Структура таблицы `76_news`
--

CREATE TABLE IF NOT EXISTS `76_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 NOT NULL,
  `description` text CHARACTER SET utf8 NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Дамп данных таблицы `76_news`
--

INSERT INTO `76_news` (`id`, `title`, `description`, `status`, `added`) VALUES
(23, 'Throwing your own personal house bash? Try these tips to spin the best tunes', 'The last thing you want to be is the guy who throws a party with old, boring playlist that runs out just when the dancing starts. But if you follow rules like this, then you are on the right path:\n\nCATER TO THE CROWD\n\nThink about what your guests like and plan your playlist accordingly. If it’s a classic-rock crowd, they’ll probably prefer AC/DC or David Bowie to hip-hop.\n\nOVERFILL YOUR PLAYLIST\n\nAdd at least 20 three-minute songs for every hour your guests will be there. If you''re mixing songs, double that number. You don''t want the music to die out before the party does.\n\nKEEP IT UPBEAT\n\nNo matter how much you love Adele''s "Someone Like You," depressing songs will kill the mood.\n\nSAVE THE GOOD STUFF\n\nDon''t put your best tracks first, while guests are still walking in. Allow some time for everyone to arrive, then break out the Michael Jackson to get everyone moving (a foolproof pick for most crowds).\n\nBRANCH OUT\n\nIf your guests love house music, you can safely add subgenres like trance, electro, and even pop to the playlist. Just steer clear of, say, death metal.\n\nAVOID AWKWARD PAUSES\n\nProgram your iPod to fade songs directly into each other, or download mixing software. It''s not as smooth as an actual DJ, but it beats the two-second silence between songs.\n', 0, '2014-10-27 20:14:51'),
(24, 'Man Who hated Britain', 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H.', 0, '2014-10-27 20:25:27'),
(25, 'Also making dance moves: Mystery Skulls, Flight Facilities, Calvin Harris & Katy Perry.', 'Dillon Francis enters Dance/Electronic Albums at No. 2 with his major-label debut, Money Sucks Friends Rule. The set, released on Mad Decent/Columbia, bows with 9,000 sold, according to Nielsen SoundScan, good also for a No. 40 entry on the Billboard 200. Six of Money''s 12 cuts have charted on Hot Dance/Electronic Songs, including "Get Low" with DJ Snake (No. 13 peak, Francis'' highest-charting single).\n\nWatch: Dillon Francis on Why He''s ''Not Selling Out'' With Debut Album\n\nFrancis is blocked from No. 1 by Disclosure, who storms back to the top for the first time in three months with Settle (12-1). Sales of the set swelled by 1,130 percent, to 10,000 units, following deep discounting at the Google Play store (to 99 cents). Disclosure also re-enters the Billboard 200 (No. 36) and the Billboard Artist 100 (No. 72).\n\nMystery Skulls soars onto Dance/Electronic Albums with the debut album Forever (No. 3, 3,000 units). Concurrently, its track "Ghost" debuts on Hot Dance/Electronic Songs at No. 17, thanks in large part to increased streaming activity. "Ghost" garnered 713,000 U.S. streams, 76 percent from YouTube, according to Nielsen BDS. Another Skulls track, "Number 1," hit the Billboard + Twitter Emerging Artists chart at No. 39 last week. That cut features vocals from Brandy and production by Nile Rodgers, as does another Forever song, “Magic.”', 0, '2014-11-07 22:14:54'),
(26, 'Fifth Harmony''s Debut Album Delayed to January, Says Camila Cabello', 'Fifth Harmony has once again pushed back the release of its debut album, Reflection, according to a Twitter post by one of the girl group''s members, Camila Cabello, on Friday afternoon (Nov. 7). Reflection was originally announced with a Nov. 17 release date in August, which then shifted back to December. The new date, according to Cabello, is Jan. 26, 2015.\n\nSingles of the Week: Fifth Harmony, Spandau Ballet & More\n\n"We''re so sorry, we love you endlessly," Cabello wrote to fans on Friday. "We''re so lucky to have you and we''ll make it up to you ... promise. JANUARY 26TH. #reflection"\n\nA rep for Fifth Harmony''s record label, Epic Records, did not immediately respond to Billboard''s request for comment.', 0, '2014-11-07 22:17:52');

-- --------------------------------------------------------

--
-- Структура таблицы `76_photo`
--

CREATE TABLE IF NOT EXISTS `76_photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 NOT NULL,
  `description` text CHARACTER SET utf8 NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Дамп данных таблицы `76_photo`
--

INSERT INTO `76_photo` (`id`, `title`, `description`, `status`, `added`) VALUES
(8, 'Test photo 1', 'Test photo 1Test photo 1Test photo 1Test photo 1Test photo 1Test photo 1', 0, '2014-11-09 00:46:15'),
(9, 'Test photo 2', 'Test photo 2Test photo 2Test photo 2Test photo 2Test photo 2', 0, '2014-11-09 03:57:46'),
(10, 'test photo 3', 'test photo 3test photo 3test photo 3', 0, '2014-11-09 04:05:05'),
(11, 'Test club photos 4', 'Test club photos 4', 0, '2015-02-04 09:55:46');

-- --------------------------------------------------------

--
-- Структура таблицы `76_push`
--

CREATE TABLE IF NOT EXISTS `76_push` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 NOT NULL,
  `added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=145 ;

--
-- Дамп данных таблицы `76_push`
--

INSERT INTO `76_push` (`id`, `title`, `added`) VALUES
(141, ' Test pushhhhhhh', '2015-02-16 22:01:42'),
(142, ' messagezz', '2015-02-16 22:51:18'),
(143, ' trsr', '2015-02-16 23:14:07'),
(144, ' Test push ', '2015-02-17 10:59:14');

-- --------------------------------------------------------

--
-- Структура таблицы `modules`
--

CREATE TABLE IF NOT EXISTS `modules` (
  `module_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `module_desc` text CHARACTER SET utf8 NOT NULL,
  `module_icon` varchar(100) CHARACTER SET utf8 NOT NULL,
  `ordering` int(3) NOT NULL,
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Дамп данных таблицы `modules`
--

INSERT INTO `modules` (`module_id`, `module_name`, `module_desc`, `module_icon`, `ordering`) VALUES
(1, 'mixes', 'Mixes', 'glyphicon glyphicon-headphones', 2),
(2, 'djs', 'Djs', 'glyphicon glyphicon-star', 3),
(3, 'users', 'Admin, moderator', 'glyphicon glyphicon-user', 8),
(4, 'events', 'Events', 'glyphicon glyphicon-calendar', 4),
(5, 'abouts', 'About Club', 'glyphicon glyphicon-star-empty', 6),
(6, 'photos', 'Photo', 'glyphicon glyphicon-star-empty', 9),
(7, 'settings', 'Settings', 'glyphicon glyphicon-list-alt', 11),
(11, 'newsies', 'Club News', 'glyphicon glyphicon-list-alt', 1),
(12, 'sendpushs', 'Send Push', 'glyphicon glyphicon-list-alt', 10);

-- --------------------------------------------------------

--
-- Структура таблицы `permissions`
--

CREATE TABLE IF NOT EXISTS `permissions` (
  `user_id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `permissions`
--

INSERT INTO `permissions` (`user_id`, `module_id`) VALUES
(2, 1),
(2, 2),
(2, 3),
(3, 1),
(3, 2),
(3, 3),
(12, 1),
(12, 2),
(13, 1),
(13, 2),
(14, 1),
(14, 2),
(15, 1),
(15, 2),
(15, 11),
(16, 1),
(16, 2),
(16, 3),
(16, 4),
(16, 5),
(16, 6),
(16, 11),
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 11),
(1, 12);

-- --------------------------------------------------------

--
-- Структура таблицы `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `role_desc` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `roles`
--

INSERT INTO `roles` (`role_id`, `role_name`, `role_desc`) VALUES
(1, 'admin', 'Administrator'),
(2, 'manager', 'Manager'),
(3, 'user', 'User');

-- --------------------------------------------------------

--
-- Структура таблицы `role_access`
--

CREATE TABLE IF NOT EXISTS `role_access` (
  `role_id` int(11) NOT NULL,
  `action_id` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `role_access`
--

INSERT INTO `role_access` (`role_id`, `action_id`) VALUES
(1, 'add'),
(1, 'edit'),
(1, 'delete'),
(1, 'publish'),
(2, 'add'),
(2, 'edit'),
(2, 'publish'),
(1, 'module'),
(1, 'ban'),
(2, 'delete');

-- --------------------------------------------------------

--
-- Структура таблицы `setting`
--

CREATE TABLE IF NOT EXISTS `setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `cnavbar` text CHARACTER SET utf8 NOT NULL,
  `cview` text CHARACTER SET utf8 NOT NULL,
  `cnavbarshadow` text CHARACTER SET utf8 NOT NULL,
  `menucolor` text CHARACTER SET utf8 NOT NULL,
  `textcolor` text CHARACTER SET utf8 NOT NULL,
  `textnavbarcolor` text CHARACTER SET utf8 NOT NULL,
  `btncolor` text CHARACTER SET utf8 NOT NULL,
  `iconcolor` text CHARACTER SET utf8 NOT NULL,
  `catptextcolor` text CHARACTER SET utf8 NOT NULL,
  `labeltopcolor` text CHARACTER SET utf8 NOT NULL,
  `labelbutcolor` text CHARACTER SET utf8 NOT NULL,
  `cellcolor` text CHARACTER SET utf8 NOT NULL,
  `site` text CHARACTER SET utf8 NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Дамп данных таблицы `setting`
--

INSERT INTO `setting` (`id`, `name`, `cnavbar`, `cview`, `cnavbarshadow`, `menucolor`, `textcolor`, `textnavbarcolor`, `btncolor`, `iconcolor`, `catptextcolor`, `labeltopcolor`, `labelbutcolor`, `cellcolor`, `site`, `status`, `added`) VALUES
(1, 'Studio76', '#1e2b44', '#1e2b44', '#f9245e', '#f9245e', '#fcfcfc', '#ffffff', '#16d987', '#f9245e', '#ffffff', '#f9245e', '#1e2b44', '#324c7d', '111', 0, '2014-11-01 02:29:36');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `user_email` text CHARACTER SET utf8 NOT NULL,
  `user_pass` text CHARACTER SET utf8 NOT NULL,
  `role_id` int(11) NOT NULL,
  `is_owner` tinyint(1) NOT NULL DEFAULT '0',
  `added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `user_email`, `user_pass`, `role_id`, `is_owner`, `added`, `status`) VALUES
(1, 'admin', 'admin@studio76.pro', '21232f297a57a5a743894a0e4a801fc3', 1, 1, '0000-00-00 00:00:00', 1),
(16, 'demo', 'demo@studio76.pro', 'fe01ce2a7fbac8fafaed7c982a04e229', 3, 0, '2014-11-09 20:12:13', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
