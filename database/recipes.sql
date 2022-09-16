-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 16, 2022 at 02:35 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `recipes`
--

-- --------------------------------------------------------

--
-- Table structure for table `app`
--

CREATE TABLE `app` (
  `id` int(6) NOT NULL,
  `about_us` text NOT NULL,
  `app_version` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `app`
--

INSERT INTO `app` (`id`, `about_us`, `app_version`) VALUES
(1, 'Kandean is a recipe sharing application, which aims to make it easier to store our favorite recipes or to share our recipes with others, with a modern design. This application will continue to be developed by Kevin Gilbert Toding as a developer.', '2.1.1');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `comment_id` int(6) NOT NULL,
  `recipe_id` int(10) NOT NULL,
  `user_id` int(6) NOT NULL,
  `comment` text NOT NULL,
  `comment_date` varchar(15) NOT NULL,
  `comment_time` varchar(20) NOT NULL,
  `edited` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`comment_id`, `recipe_id`, `user_id`, `comment`, `comment_date`, `comment_time`, `edited`) VALUES
(73, 8, 4, 'this is the newst comment', '2022-08-06', '12:39:53', 0),
(82, 6, 4, 'test', '2022-08-07', '08:20:34', 0),
(109, 7, 5, 'I in save ya kak 🥰', '2022-08-22', '05:55:06', 0),
(110, 5, 2, '😋😍', '2022-08-28', '07:59:40', 0),
(116, 5, 20, 'enak', '2022-09-07', '04:09:53', 0),
(153, 59, 4, 'Ini takaran gulanya berapa sendok ya, soalnya tidak dilampirkann😅', '2022-09-13', '20:12:20', 0),
(154, 59, 2, 'dddddddd', '2022-09-14', '03:44:23', 0),
(155, 59, 2, 'dddddddd', '2022-09-14', '03:44:30', 0),
(156, 59, 2, 'ssss', '2022-09-14', '03:44:46', 0),
(158, 59, 2, 'xxxx', '2022-09-14', '03:45:40', 0),
(159, 59, 2, 'ddd', '2022-09-14', '03:47:42', 0),
(161, 59, 2, 'ddd', '2022-09-14', '03:51:33', 0),
(162, 59, 2, 'dddd', '2022-09-14', '03:54:16', 0),
(164, 59, 2, 'hii', '2022-09-14', '03:56:40', 0),
(165, 59, 2, 'sssss', '2022-09-14', '03:59:28', 0),
(166, 59, 2, 'sdsdsd', '2022-09-14', '03:59:41', 0),
(167, 59, 2, 'kkk', '2022-09-14', '04:00:43', 0),
(227, 10, 4, 'ygddDDDD', '2022-09-14', '21:56:49', 1),
(228, 7, 2, 'ggg', '2022-09-14', '22:20:44', 0),
(229, 10, 2, 'JUHHJHJ', '2022-09-15', '09:35:33', 0),
(230, 5, 4, 'wkwkwk friky🥰🥰', '2022-09-15', '09:38:01', 1),
(231, 10, 2, 'tsdsdsds', '2022-09-15', '09:46:22', 1),
(234, 7, 4, 'sdsdsds', '2022-09-15', '09:48:13', 0),
(241, 7, 2, 'terima kasih ngab', '2022-09-15', '10:50:50', 0),
(242, 7, 2, 'xxx', '2022-09-15', '10:51:24', 0),
(243, 10, 2, 'jssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff', '2022-09-15', '10:59:23', 1),
(244, 7, 2, 'hahhah', '2022-09-15', '13:20:53', 0),
(247, 10, 2, 'yyyu', '2022-09-15', '13:45:09', 0),
(248, 7, 2, 'DFDFDFDFDF', '2022-09-15', '13:46:20', 0),
(249, 7, 2, 'guuDSSDDSDSS', '2022-09-15', '13:46:35', 1),
(250, 10, 2, 'lalalaala', '2022-09-15', '13:48:20', 0),
(252, 10, 2, 'fdfdfdfdfdfdfdf', '2022-09-15', '14:13:26', 0),
(255, 10, 2, 'yryryr', '2022-09-15', '19:43:04', 0);

-- --------------------------------------------------------

--
-- Table structure for table `followers`
--

CREATE TABLE `followers` (
  `id` int(20) NOT NULL,
  `user_id` int(10) NOT NULL,
  `followers_id` int(10) NOT NULL,
  `date` varchar(80) NOT NULL,
  `time` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `followers`
--

INSERT INTO `followers` (`id`, `user_id`, `followers_id`, `date`, `time`) VALUES
(60, 4, 6, '2022-09-10', '05:16:45'),
(75, 6, 4, '2022-09-11', '21:13:59'),
(78, 18, 6, '2022-09-12', '20:18:23'),
(122, 4, 2, '2022-09-15', '18:17:42');

-- --------------------------------------------------------

--
-- Table structure for table `following`
--

CREATE TABLE `following` (
  `id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `following_id` int(10) NOT NULL,
  `date` varchar(80) NOT NULL,
  `time` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `following`
--

INSERT INTO `following` (`id`, `user_id`, `following_id`, `date`, `time`) VALUES
(55, 6, 4, '2022-09-10', '05:16:45'),
(57, 6, 2, '2022-09-10', '05:21:21'),
(70, 4, 6, '2022-09-11', '21:13:59'),
(73, 6, 18, '2022-09-12', '20:18:23'),
(117, 2, 4, '2022-09-15', '18:17:42');

-- --------------------------------------------------------

--
-- Table structure for table `liked`
--

CREATE TABLE `liked` (
  `like_id` int(6) NOT NULL,
  `user_id` int(6) NOT NULL,
  `recipe_id` int(6) NOT NULL,
  `code` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `liked`
--

INSERT INTO `liked` (`like_id`, `user_id`, `recipe_id`, `code`) VALUES
(204, 2, 6, 1),
(217, 2, 58, 1),
(220, 5, 9, 1),
(222, 5, 3, 1),
(229, 2, 5, 1),
(230, 2, 57, 1),
(231, 2, 2, 1),
(232, 6, 9, 1),
(236, 2, 9, 1),
(237, 20, 5, 1),
(239, 20, 59, 1),
(240, 20, 9, 1),
(241, 20, 10, 1),
(243, 20, 7, 1),
(247, 2, 56, 1),
(251, 2, 2, 1),
(252, 20, 1, 1),
(255, 4, 8, 1),
(256, 20, 8, 1),
(262, 2, 2, 1),
(263, 2, 2, 1),
(264, 2, 2, 1),
(276, 4, 10, 1),
(278, 2, 7, 1),
(279, 4, 59, 1),
(280, 4, 5, 1),
(281, 4, 7, 1),
(289, 2, 4, 1),
(355, 2, 59, 1),
(357, 2, 8, 1),
(358, 4, 60, 1),
(361, 4, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `notif_id` int(50) NOT NULL,
  `user_id` int(10) NOT NULL,
  `user_id_notif` int(10) NOT NULL,
  `recipe_id` int(10) NOT NULL DEFAULT 0,
  `type` varchar(80) NOT NULL,
  `comment` text NOT NULL,
  `date` varchar(80) NOT NULL,
  `time` varchar(80) NOT NULL,
  `status` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`notif_id`, `user_id`, `user_id_notif`, `recipe_id`, `type`, `comment`, `date`, `time`, `status`) VALUES
(18, 20, 4, 1, 'like', '', '2022-09-11', '20:46:45', 0),
(21, 4, 2, 8, 'like', '', '2022-09-11', '21:11:28', 0),
(23, 4, 6, 0, 'follow', '', '2022-09-11', '21:13:59', 1),
(25, 20, 2, 8, 'like', '', '2022-09-12', '15:14:23', 0),
(28, 4, 0, 10, 'like', '', '2022-09-12', '15:47:43', 1),
(32, 2, 1, 2, 'like', '', '2022-09-12', '17:15:14', 1),
(36, 4, 0, 10, 'like', '', '2022-09-12', '17:23:11', 1),
(38, 6, 18, 0, 'follow', '', '2022-09-12', '20:18:23', 1),
(78, 4, 2, 59, 'like', '', '2022-09-13', '20:13:01', 0),
(79, 2, 0, 59, 'comment', 'dddddddd', '2022-09-14', '03:44:23', 1),
(80, 2, 0, 59, 'comment', 'dddddddd', '2022-09-14', '03:44:30', 1),
(81, 2, 0, 59, 'comment', 'ssss', '2022-09-14', '03:44:46', 1),
(82, 2, 0, 59, 'comment', 'xxxx', '2022-09-14', '03:45:40', 1),
(139, 4, 2, 7, 'comment', 'sdsdsds', '2022-09-15', '09:48:13', 0),
(148, 2, 4, 10, 'comment', 'jssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff', '2022-09-15', '10:59:23', 0),
(150, 2, 4, 10, 'comment', 'gghhff', '2022-09-15', '13:43:19', 0),
(151, 2, 4, 10, 'comment', 'yyyu', '2022-09-15', '13:45:09', 0),
(152, 2, 4, 10, 'comment', 'lalalaala', '2022-09-15', '13:48:20', 0),
(154, 2, 4, 10, 'comment', 'fdfdfdfdfdfdfdf', '2022-09-15', '14:13:26', 0),
(166, 2, 4, 0, 'follow', '', '2022-09-15', '18:17:42', 0),
(174, 2, 4, 4, 'like', '', '2022-09-15', '18:53:49', 0),
(235, 2, 4, 10, 'comment', 'yryryr', '2022-09-15', '19:43:04', 0),
(236, 2, 4, 10, 'comment', 'hfhdhdh', '2022-09-15', '19:43:34', 0);

-- --------------------------------------------------------

--
-- Table structure for table `recipe`
--

CREATE TABLE `recipe` (
  `recipe_id` int(10) NOT NULL,
  `user_id` int(6) NOT NULL,
  `title` varchar(150) NOT NULL,
  `description` text NOT NULL,
  `category` varchar(80) NOT NULL,
  `servings` varchar(80) NOT NULL,
  `duration` varchar(80) NOT NULL,
  `ingredients` text NOT NULL,
  `steps` text NOT NULL,
  `note` text NOT NULL,
  `upload_date` varchar(50) NOT NULL,
  `upload_time` varchar(50) NOT NULL,
  `image` varchar(300) NOT NULL DEFAULT 'default.png',
  `status` int(1) NOT NULL,
  `ratings` varchar(80) NOT NULL,
  `likes` int(20) NOT NULL,
  `qrcode_img` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `recipe`
--

INSERT INTO `recipe` (`recipe_id`, `user_id`, `title`, `description`, `category`, `servings`, `duration`, `ingredients`, `steps`, `note`, `upload_date`, `upload_time`, `image`, `status`, `ratings`, `likes`, `qrcode_img`) VALUES
(1, 4, 'Rendang Daging Sapi Sederhana', 'Rendang sapi menjadi salah satu kuliner khas Indonesia yang paling terkenal di dunia. Bahkan dinobatkan menjadi makanan terlezat di dunia versi cnn.\r\n\r\n', 'Meat', '10 people', '3 hour 24 minutes', '1 kg daging sapi\r\n2 batang serai\r\n6 lembar daun jeruk\r\n4 lembar daun salam\r\n2 cm kayu manis\r\n1 sdm gula merah\r\n1 sdm kaldu bubuk rasa sapi\r\n1 sdt jintan bubuk\r\n1 sdt merica\r\n1 buah jeruk lemon\r\n1000 ml santan dari 1 butir kelapa\r\nsecukupnya Minyak goreng\r\nBumbu yang dihaluskan:\r\n8 siung bawang putih\r\n12 siung bawang merah\r\n4 buah kemiri\r\n1 sdt ketumbar\r\n6 buah cabe merah besar (buang bijinya)\r\n50 gram cabe keriting merah\r\n4 cm kunyit\r\n6 cm jahe', '1. Cuci bersih daging, potong sesuai selera beri perasan jeruk lemon, lalu cuci lagi.\r\n2. Haluskan bumbu halus\r\n3. Tumis bumbu halus dengan serai, daun jeruk, daun salam, kayu manis dan lengkuas sampai matang.\r\n4. Masukkan daging, aduk rata lalu masukkan santan. Masak dengan api kecil sambil sesekali diaduk.\r\n5. Kemudian masukkan jintan, garam, kaldu bubuk, merica, dan gula merah, diamkan hingga bumbu meresap dan airnya menyusut. Koreksi rasa, angkat dan hidangkan.', '', '2022-07-22', '12.30.30', '1.png', 1, '8/10', 26, ''),
(2, 4, 'Sate sapi ketumbar', 'Sate pada umumnya hanya berbalur bumbu kecap atau pun bumbu kacang. Nah, bagaimana bila ditambahkan ketumbar?\r\nDengan menggunakan ketumbar, sate sapi biasa pasti jadi lebih istimewa karena rasanya yang lebih berkarakter. Dengan tambahan Bango Kecap Manis, paduan aromatic ketumbar serta manis legit kecap akan membuat sate ini disukai siapa pun yang melahapnya.', 'Meat', '4 People', '2 hour 54 minutes', '1. 300 g daging sapi has luar, potong dadu \r\n   3 cm\r\n2. 160 ml Bango Kecap Manis\r\n3. 1 sdm ketumbar bubuk\r\n4. 2 sdm minyak\r\n5. 1 sdm air asam jawa\r\n6. Sekitar 10 tusuk sate\r\n7. 3 batang daun ketumbar, petik daunnya\r\n ', '1. Dengan menggunakan blender, haluskan bumbu sate.\r\n2. Lumuri daging sapi dengan bumbu halus, Bango Kecap Manis, ketumbar bubuk, minyak, dan air asam jawa. Diamkan selama 30 menit.\r\n3. Tusukkan daging dengan tusuk sate.\r\nBakar di atas bara api hingga matang sambil sesekali diolesi bumbu. Angkat.\r\n4. Tata di atas piring saji. Taburi dengan daun ketumbar.\r\n ', '', '2022-07-22', '12.30.30', '2.png', 1, '9/10', 14, ''),
(3, 4, 'Mie Ayam Solo', 'Mie ayam dari daerah ini menggunakan lebih banyak bumbu, terutama untuk ayam kecapnya. Selain kunyit, rempah-rempah yang digunakan adalah jahe, kemiri, dan ketumbar.', 'Noodle', '3-4 people', '1 hour 45 minutes', '1. 500 gram mie telur/mie basah\r\n2. 250 gram daging ayam, rebus dan potong 3. kotak-kotak\r\n4. 100 ml kecap asin\r\n5. 10 ml kecap manis\r\n6. 15 ml kecap inggris\r\n7. 2 batang daun bawang, iris halus\r\n8. 1 ikat sawi hijau/caisim, potong 5 cm', '1. Persiapkan kaldu ayam terlebih dahulu. Rebus tulang ayam dan 250 gram daging ayam yang akan dipakai untuk mie dengan 2000 ml air. Tambahkan merica dan garam. Masak hingga kuah mendidih dan ayam matang.\r\n2. Ambil daging ayam rebus yang akan digunakan untuk mie, kemudian potong dadu.\r\n3. Haluskan semua bahan-bahan bumbu. Panaskan sedikit minyak goreng, kemudian tumis bumbu hingga harum.\r\n4. Masukkan daging ayam yang sudah direbus dan dipotong dadu, kemudian bumbui lagi dengan sedikit gula pasir.\r\n5. Selanjutnya kita akan membuat minyak ayam. Tuang minyak sayur di wajan. Masukkan kulit dan lemak ayam. Masak dengan api kecil hingga kulit dan lemak ayam kering. Angkat dari penggorengan, kemudian masukkan bawang putih cincang. Masak sampai bawang putih garing dan minyak ayam siap digunakan.\r\n6. Rebus mie telur/mie basah bersama sawi, kemudian tiriskan airnya.\r\n7. Siapkan mangkuk, kemudian masukkan 1 sendok minyak ayam. Aduk bersama mie dan sawi hijau. Tambahkan air rebusan kaldu ke dalam mangkuk.\r\n8. Tuangkan tumisan ayam di atasnya, lalu taburi bawang goreng dan irisan daun bawang.\r\n9. Sajikan mie ayam Solo selagi hangat. Lengkapi dengan saus sambal dan saus tomat di wadah terpisah.', '', '2022-07-25', '11:40:20', '3.png', 1, '9.5/10', 56, ''),
(4, 4, 'Ramen Special', 'Ramen telah menjadi salah satu makanan penghibur utama di Jepang. Ramen menjadi populer berkat masakan Cina di restoran Tokyo yang menyajikan shina soba (Shina yang berarti Cina dan soba yang berarti mie soba). Selama bertahun-tahun, shina soba menjadi salah satu masakan Cina paling populer di Jepang.', 'Noodle', '1-2 people', '1 hour 23 minutes', '1. 100 gram mie ramen\r\n150 gram udang kupas\r\n2. Daun sawi hijau (secukupnya, potong sesuai selera)\r\n3. Kacang polong (secukupnya)\r\n4. 5 iris daging yang sudah direbus dengan kaldu ayam dan bumbu garam\r\n5. 1 buah telur (iris jadi dua bagian)\r\n6. Minyak goreng (secukupnya, untuk menumis bumbu)\r\n7. Air (secukupnya)', '1. Rebus mie ramen hingga matang menggunakan air kaldu ayam lalu tiriskan dan sisihkan sebentar.\r\n2. Panaskan minyak, tumis bawang putih dan bawang bombay hingga harum.\r\n3. Tambahkan daun sawi, kacang polong dan bumbu halus ke dalamnya.\r\n4. Tambahkan air dan didihkan.\r\n5. Jika sudah mendidih, masukkan mie dan masak hingga kembali mendidih.\r\n6. Tambahkan tepung sagu yang sudah dilarutkan ke dalam mie ramen dan masak hingga meletup-letup.\r\n7. Sajikan di mangkok saji dan tambahkan irisan daging juga telur di atasnya.', '', '2022-07-25', '11:40:20', '4.png', 1, '7/10', 78, ''),
(5, 2, 'Ayam Bakar Kecap Manis', 'Kali ini, kita akan mencoba cara membuat ayam bakar gurih nikmat yang diungkep dengan bumbu halus, santan, gula merah, dan kecap manis. Ungkep, berasal dari bahasa Jawa, adalah proses memasak bahan makanan yang sudah dibumbui dengan api kecil dan dalam waktu yang lama. Setelah ayam diungkep hingga empuk, panggang ayam sambil diolesi bumbu dan kecap.', 'Meat', '1-2 people', '2 hour 4 minutes', '1. 800 g (1 ekor) ayam pejantan, potong jadi 4 bagian\r\n2. 4 lembar daun salam\r\n3. 2 batang serai, memarkan\r\n4. 20 g asam Jawa, larutkan dengan 3 sdm air panas \r\n5. 500 ml santan\r\n6. 2 sdm minyak, untuk menumis\r\n7. 10 butir bawang merah\r\n8. 5 siung bawang putih\r\n9. 1 sdm ketumbar, sangrai\r\n10. 5 butir kemiri\r\n11. ½ sdt merica bubuk\r\n12. 3 cm kunyit\r\n13. 50 g gula merah, sisir halus\r\n14. 1 sdt garam\r\n15. 1 sdt gula pasir', '1. Panaskan minyak, tumis bumbu halus, daun salam, dan serai hingga harum.\r\n2. Masukkan air larutan asam Jawa dan ayam, aduk rata.\r\n3. Tuangkan santan, aduk perlahan agar santan tidak pecah.\r\n4. Masak hingga santan meresap dan ayam empuk. Angkat dan sisihkan.\r\n5. Panaskan wajan, panggang ayam sambil diolesi bumbu halus dan kecap. Panggang hingga berwarna kecokelatan.\r\n6. Angkat dan sajikan.', '', '2022-07-25', '12.30.30', '5.png', 1, '6/10', 14, ''),
(7, 2, 'Sayur Bayam Bening', 'Sayur bayam bukan hanya kaya gizi tapi juga enak diolah menjadi menu makanan apa saja, mulai dari sayur bening hingga goreng sebagai keripik. Ini sekian resep sayur bayam enak yang bisa dicaba di rumah.', 'Vegetables', '2-3 people', '35 minutes', '1 ikat bayam\r\n1 buah jagung manis\r\n2 siung bawang putih\r\n3 siung bawang merah\r\n1 ruas kunci\r\nGaram, gula dan kaldu bubuk secukupnya', 'Siangi bayam, cuci bersih lalu sisihkan.\r\n\r\nPotong jagung manis menjadi 3 bagian, sisihkan.\r\n\r\nIris halus bawang merah dan bawang putih, geprek kunci.\r\n\r\nDidihkan air, masukkan jagung manis dan bawang merah, bawang putih dan kunci.\r\n\r\nMasak hingga setengah matang.\r\n\r\nMasukkan bayam, lalu tambahkan garam, gula dan kaldu bubuk secukupnya. Tes rasa.\r\n\r\nSayur bayam yang segar dan enak siap disajikan.', '', '2022-07-25', '11:40:20', '7.png', 1, '4/10', 7, ''),
(8, 2, 'Jus Jeruk', 'Resep jus jeruk super segar dan mudah untuk dibuat', 'Drinks', '1 peo', '15 minutes', '3 Buah Jeruk\r\n1 cup susu kental manis\r\nEs Batu\r\nAir gula (jika diinginkan)', 'Masukan jeruk kedalam blender\r\n\r\nMasukan air,es batu dengan air gula, lalu diaduk\r\n\r\nLalu sajikan dengan gelas dan diberi hiasan kulit jeruk.', '', '2022-07-25', '12.30.30', '2-Jus Jeruk-2022-08-15.png', 1, '7/10', 74, ''),
(9, 4, 'Bakso Sapi Istimewa', 'Bakso istimewa dijamin enakk', 'Meat', '2-3 people', '1 hour 42 minutes', '3 ons daging sapi giling\r\n4 sendok tepung kanji (jangan sampai menggunung)\r\n3 sendok tepung terigu (jangan sampai menggunung\r\n2 butir telur\r\n1 siung bawang putih\r\nsecukupnya Garam\r\nsecukupnya Kaldu jamur\r\nsecukupnya Merica\r\nsecukupnya Air untuk merebus', 'Siapkan daging, tepung kanji, tepung terigu, garam, kaldu jamur, dan merica pada mangkuk besar atau baskom.\r\n\r\nDidihkan air untuk merebus bakso. \r\n\r\nKemudian blender bawang putih dengan 1 butir telur.\r\n\r\nSetelah itu tuangkan telur yg sudah di blender pada adonan bakso tadi. Aduk sampai rata. Lalu, masukkan telur 1 butir. Aduk sampai kalis. Jangan lupa koreksi rasa.\r\n\r\nSetelah air mendidih dan adonan sudah siap, buatlah bakso dengan menggunakan sendok. Dan ketika bakso sudah mengapung, angkat dan tiriskan.\r\n\r\nBakso sapi siap untuk diolah untuk berbagai macam masakan.', 'Gunakan garam secukupnya', '2022-07-25', '11:40:20', '9.png', 0, '9/10', 93, ''),
(10, 4, 'Jus strawberry', 'Resep jus strawberry sederhana yang nikmat dan menyegarkan', 'Drinks', '1 people', '15 minutes', '10 buah strawberry\n5 sdm kental manis putih\n2 sdm gula pasir\n200 ml air matang\n6 kotak kecil es batu', 'Cuci buah strawberry sampai bersih, kemudian buang bagian tangkainya\r\n\r\nPotong2 buah strawberry, masukkan kedalam blender tambahkan air, gula pasir, kental manis dan es batu\r\n\r\nBlender sampai tercampur rata dan halus\r\n\r\nSiap disajikan', 'Gunakan strawberry yang benar benar matang agar rasanya tidak asam, serta tambahkan susu bubuk atau madu agar menambah kelezatannya', '2022-07-25', '12.30.30', '10.png', 1, '10/10', 92, ''),
(56, 2, 'Sate Taichan', 'Sate enak', 'Meat', '2', '2 hour 34 minutes', '• Daging ayam\n• kecap\n• cabai', '• Bakar sate ke arang', 'testt notes', '2022-08-15', '05:42:41', '2-Sate Taichan-2022-08-15.png', 1, '', 1, '2-Sate Taichan-2022-08-15.png'),
(57, 2, 'Papiong', 'Makanan khas toraja', 'Meat', '10', '2 hour 32 minutes', '• Pork\n• bamboo\n• salt\n• vegetables', '• Put the pork in to bamboo\n• burn it', 'Kurangi garam', '2022-08-21', '14:44:59', '2-Papiong-2022-08-21.png', 1, '', 1, '2-Papiong-2022-08-21.png'),
(58, 2, 'Sate taichan', 'enakk', 'Meat', '2', '2 hour', '• meat\n• saos', '• bakar sate', 'tambahkan sayur jika perlu', '2022-08-21', '15:05:12', '2-Sate taichan-2022-08-21.png', 2, '', 1, '2-Sate taichan-2022-08-21.png'),
(59, 2, 'just jeruk', 'segar', 'Drinks', '2', '32 minutes', '•jerul\n• sugar', '• peras jeruk', 'tambahkan gula', '2022-08-21', '15:07:14', '2-just jeruk-2022-08-21.png', 1, '', 3, '2-just jeruk-2022-08-21.png'),
(60, 4, 'Souo ayam', 'sup ayam nikmat', 'Meat', '2', '3hour 2 minutes', '• Daging ayam\n• sayur selederi\n• garam', '• pnaskan air terlebih dahulu', 'hidangkan bersama keluarga', '2022-09-16', '07:25:02', '4-Souo ayam-2022-09-16.png', 1, '', 1, '4-Souo ayam-2022-09-16.png');

-- --------------------------------------------------------

--
-- Table structure for table `report_bug`
--

CREATE TABLE `report_bug` (
  `report_id` int(20) NOT NULL,
  `user_id` int(6) NOT NULL,
  `title` varchar(300) NOT NULL,
  `report` text NOT NULL,
  `image` varchar(300) NOT NULL,
  `date` varchar(12) NOT NULL,
  `time` varchar(12) NOT NULL,
  `status` smallint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `report_bug`
--

INSERT INTO `report_bug` (`report_id`, `user_id`, `title`, `report`, `image`, `date`, `time`, `status`) VALUES
(3, 4, '', 'halooo test chat pertama', 'img_chat/4-2022-08-19-.png', '2022-08-19', '05:29:58', 2),
(4, 4, '', 'halooo test chat pertama', '4-2022-08-19-.png', '2022-08-19', '05:30:57', 0),
(6, 4, '', 'halooo test chat pertama', '4-2022-08-19-05:32:39.png', '2022-08-19', '05:32:39', 1),
(7, 4, '', 'halooo test chat pertama', '4-2022-08-19-.png', '2022-08-19', '05:32:57', 1),
(8, 4, '', 'halooo test chat pertama', '4-2022-08-19-05:33:18.png', '2022-08-19', '05:33:18', 1),
(9, 4, '', 'halooo test chat pertama', '4-2022-08-19-05:33:36-.png', '2022-08-19', '05:33:36', 1),
(10, 4, '', 'halooo test chat pertama', '4-2022-08-19-SELECT MAX(id) AS max_id FROM chat.png', '2022-08-19', '05:40:23', 1),
(11, 4, '', 'halooo test chat pertama', '4-2022-08-19-SELECT MAX(id) FROM message.png', '2022-08-19', '05:41:13', 1),
(12, 4, '', 'halooo test chat pertama', '4-2022-08-19-12.png', '2022-08-19', '05:42:43', 1),
(13, 4, '', 'halooo test chat pertama', '4-2022-08-19-13.png', '2022-08-19', '05:42:53', 1),
(14, 2, '', 'Ada error ', '2-2022-08-19-14.png', '2022-08-19', '18:13:06', 1),
(15, 2, '', 'hhhhh', '2-2022-08-19-15.png', '2022-08-19', '18:16:23', 1),
(16, 2, '', 'halooo test chat pertama', '2-2022-08-19-16.png', '2022-08-19', '21:29:28', 1);

-- --------------------------------------------------------

--
-- Table structure for table `report_recipe`
--

CREATE TABLE `report_recipe` (
  `report_id` int(12) NOT NULL,
  `user_id` int(6) NOT NULL,
  `title` varchar(255) NOT NULL,
  `report` text NOT NULL,
  `image` varchar(300) NOT NULL,
  `recipe_id` int(6) NOT NULL,
  `date` varchar(300) NOT NULL,
  `time` varchar(300) NOT NULL,
  `status` smallint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `report_recipe`
--

INSERT INTO `report_recipe` (`report_id`, `user_id`, `title`, `report`, `image`, `recipe_id`, `date`, `time`, `status`) VALUES
(1, 2, '', 'haloooo', '', 5, '2022-08-19', '21:28:40', 1),
(2, 0, '', '10', '', 2, '2022-08-19', '21:58:05', 1),
(3, 2, '', 'sssssssssss', '', 10, '2022-08-19', '22:01:28', 1),
(4, 2, '', 'sssssssssss', '', 10, '2022-08-19', '22:04:01', 1),
(5, 2, '', 'sssss', '', 10, '2022-08-19', '22:04:43', 1),
(11, 2, 'testss', 'yaaaaaaa', '2-2022-08-31-11.png', 9, '2022-08-31', '12:01:11', 2);

-- --------------------------------------------------------

--
-- Table structure for table `report_user`
--

CREATE TABLE `report_user` (
  `report_id` int(20) NOT NULL,
  `user_id` int(6) NOT NULL,
  `user_id_report` int(6) NOT NULL,
  `title` varchar(255) NOT NULL,
  `report` text NOT NULL,
  `image` varchar(300) NOT NULL,
  `date` varchar(80) NOT NULL,
  `time` varchar(80) NOT NULL,
  `status` smallint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `report_user`
--

INSERT INTO `report_user` (`report_id`, `user_id`, `user_id_report`, `title`, `report`, `image`, `date`, `time`, `status`) VALUES
(8, 2, 3, 'user menjiplak resep saya', 'test my first report', ' 2-2022-08-29-8.png', '2022-08-29', '12:59:54', 1),
(9, 4, 2, 'User menjiplak resep sayaa', 'Resep saya Cara membuat makanan papion khas toraja ditiru Dan did upload tanpa seizin saya', ' 4-2022-08-29-9.png', '2022-08-29', '13:07:03', 0),
(13, 2, 4, 'User melakukan chat spam ke akun saya Dan menjiplak resep syaa', 'Saya melaporkan user ini Karen user telah melakukan aktivitas chat spam kepada saya Dan mencoba menduplicate resep saya', '2-2022-08-30-13.png', '2022-08-30', '05:24:32', 0),
(14, 2, 0, 'recipe ini mengandung sara', 'saya melaporkan recipe ini karena postingin ini mengandung sara', '2-2022-08-31-10.png', '2022-08-31', '05:48:58', 1),
(15, 2, 4, 'User plagiarized my recipe', 'The user plahiarized my recipe entitled special orange juice, recipe and shared it without my knowledge, and the user got quite a lot of total likes 😭😭', '2-2022-09-03-15.png', '2022-09-03', '16:22:50', 1);

-- --------------------------------------------------------

--
-- Table structure for table `saved`
--

CREATE TABLE `saved` (
  `save_id` int(6) NOT NULL,
  `user_id` int(6) NOT NULL,
  `recipe_id` int(10) NOT NULL,
  `code` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `saved`
--

INSERT INTO `saved` (`save_id`, `user_id`, `recipe_id`, `code`) VALUES
(1, 4, 2, 1),
(140, 2, 6, 1),
(144, 2, 59, 1),
(145, 5, 7, 1),
(146, 2, 5, 1),
(147, 2, 2, 1),
(148, 6, 7, 1),
(149, 2, 9, 1),
(153, 20, 7, 1),
(154, 20, 10, 1),
(160, 4, 59, 1),
(161, 4, 10, 1),
(163, 2, 7, 1),
(164, 4, 60, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(6) NOT NULL,
  `username` varchar(80) NOT NULL,
  `email` varchar(80) NOT NULL,
  `password` varchar(150) NOT NULL,
  `date` varchar(80) NOT NULL,
  `time` varchar(80) NOT NULL,
  `photo_profile` varchar(255) NOT NULL DEFAULT 'default.png',
  `biography` text NOT NULL,
  `verified` tinyint(1) NOT NULL DEFAULT 0,
  `role` tinyint(1) NOT NULL DEFAULT 2,
  `active` smallint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `email`, `password`, `date`, `time`, `photo_profile`, `biography`, `verified`, `role`, `active`) VALUES
(2, 'Chika Zeruya', 'zeruya@gmail.com', '$2y$10$6pJk0gcDyPaPKTghhW8wN.Bw4tXQK3JEXGP1I93qTjXyv8RhaEnp6', '23-07-2022', '15:16:23', '2.png', 'I love cooking 😍', 0, 2, 1),
(4, 'Kevin Gilbert', 'gwareshop@gmail.com', '$2y$10$sVD6yM/NE5CnXMZ9wK2WueqeKF.BkBEFgFk9rVK4nrrZ2O6hWj8Ni', '23-07-2022', '15:20:03', '4.png', 'Kevin Gilbert Toding', 0, 2, 1),
(6, 'Super Admin', 'superadmin@gmail.com', '$2y$10$7b65g9mv6KKLDcprJJfZiOltw2.WNquSTsKl8zj15wOpfK85QM3ky', '22-08-2022', '06:19:00', '6.png', '', 1, 1, 1),
(8, 'natalia Permata Sari', 'natalia@gmail.com', '$2y$10$vhAQRqBCdjQDRa91nSu5M.MEztFdhZHtmJBPbWg6N/qjc3Oc2JlPW', '28-08-2022', '09:27:09', '8.png', '', 0, 2, 1),
(18, 'Adelaide Gabriella', 'natalia2', '$2y$10$j/p0p.xJe3S8eyQstOc5G.GeAe7uzlmqlBQvmU10p2QIMnpLiyVgm', '23', '40', '18.png', '', 0, 2, 1),
(19, 'Fajar Nugroho', 'kevin@gmail.com', '$2y$10$G6J.YHQ6nUHwELdjCSiAcOX1iXiQaFA4nxz9uhdr.REXcrlS7/D/S', '28-08-2022', '10:36:48', '19.png', '', 0, 2, 0),
(20, 'Eve Ichwan', 'eve@gmail.com', '$2y$10$9dBbInqd8dqvBSWNtFs/M.Q/SLNMaJt7VC.iAwDd6ukKw1L54XiFq', '07-09-2022', '04:08:42', '20.png', 'Halooo', 0, 2, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `app`
--
ALTER TABLE `app`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`comment_id`);

--
-- Indexes for table `followers`
--
ALTER TABLE `followers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `following`
--
ALTER TABLE `following`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `liked`
--
ALTER TABLE `liked`
  ADD PRIMARY KEY (`like_id`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`notif_id`);

--
-- Indexes for table `recipe`
--
ALTER TABLE `recipe`
  ADD PRIMARY KEY (`recipe_id`);

--
-- Indexes for table `report_bug`
--
ALTER TABLE `report_bug`
  ADD PRIMARY KEY (`report_id`);

--
-- Indexes for table `report_recipe`
--
ALTER TABLE `report_recipe`
  ADD PRIMARY KEY (`report_id`);

--
-- Indexes for table `report_user`
--
ALTER TABLE `report_user`
  ADD PRIMARY KEY (`report_id`);

--
-- Indexes for table `saved`
--
ALTER TABLE `saved`
  ADD PRIMARY KEY (`save_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `app`
--
ALTER TABLE `app`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `comment_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=260;

--
-- AUTO_INCREMENT for table `followers`
--
ALTER TABLE `followers`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;

--
-- AUTO_INCREMENT for table `following`
--
ALTER TABLE `following`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=119;

--
-- AUTO_INCREMENT for table `liked`
--
ALTER TABLE `liked`
  MODIFY `like_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=362;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `notif_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=240;

--
-- AUTO_INCREMENT for table `recipe`
--
ALTER TABLE `recipe`
  MODIFY `recipe_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `report_bug`
--
ALTER TABLE `report_bug`
  MODIFY `report_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `report_recipe`
--
ALTER TABLE `report_recipe`
  MODIFY `report_id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `report_user`
--
ALTER TABLE `report_user`
  MODIFY `report_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `saved`
--
ALTER TABLE `saved`
  MODIFY `save_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=165;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
