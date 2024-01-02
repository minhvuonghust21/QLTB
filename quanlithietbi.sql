-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quanlithietbi
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `loaithietbi`
--

DROP TABLE IF EXISTS `loaithietbi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaithietbi` (
  `maloaitb` varchar(45) NOT NULL,
  `tenloaitb` varchar(45) NOT NULL,
  `mota` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`maloaitb`),
  UNIQUE KEY `maloaitb_UNIQUE` (`maloaitb`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaithietbi`
--

LOCK TABLES `loaithietbi` WRITE;
/*!40000 ALTER TABLE `loaithietbi` DISABLE KEYS */;
INSERT INTO `loaithietbi` VALUES ('1','Thí nghiệm','dung trong phong thi nghiem');
/*!40000 ALTER TABLE `loaithietbi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `muon`
--

DROP TABLE IF EXISTS `muon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `muon` (
  `maphieumuon` varchar(45) NOT NULL,
  `matb` varchar(45) NOT NULL,
  `ngaytra` date DEFAULT NULL,
  `trangthaiTB` varchar(45) NOT NULL,
  `tentb` varchar(45) NOT NULL,
  KEY `muon_maphieumuon_idx` (`maphieumuon`),
  KEY `muon_matb_idx` (`matb`),
  CONSTRAINT `muon_maphieumuon` FOREIGN KEY (`maphieumuon`) REFERENCES `phieumuon` (`maphieumuon`),
  CONSTRAINT `muon_matb` FOREIGN KEY (`matb`) REFERENCES `thietbi` (`matb`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `muon`
--

LOCK TABLES `muon` WRITE;
/*!40000 ALTER TABLE `muon` DISABLE KEYS */;
INSERT INTO `muon` VALUES ('1','2','2023-01-23','Đã trả','máy in');
/*!40000 ALTER TABLE `muon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoimuon`
--

DROP TABLE IF EXISTS `nguoimuon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nguoimuon` (
  `manguoimuon` varchar(45) NOT NULL,
  `tennguoimuon` varchar(45) NOT NULL,
  `khoavien` varchar(45) NOT NULL,
  `chucvu` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`manguoimuon`),
  UNIQUE KEY `manguoimuon_UNIQUE` (`manguoimuon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoimuon`
--

LOCK TABLES `nguoimuon` WRITE;
/*!40000 ALTER TABLE `nguoimuon` DISABLE KEYS */;
INSERT INTO `nguoimuon` VALUES ('2019','Tuan','DTVT','Sinh vien','Tuanvip@mail');
/*!40000 ALTER TABLE `nguoimuon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoisudung`
--

DROP TABLE IF EXISTS `nguoisudung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nguoisudung` (
  `taikhoan` varchar(20) NOT NULL,
  `matkhau` varchar(45) NOT NULL,
  `chucvu` varchar(45) NOT NULL,
  PRIMARY KEY (`taikhoan`),
  UNIQUE KEY `taikhoan_UNIQUE` (`taikhoan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoisudung`
--

LOCK TABLES `nguoisudung` WRITE;
/*!40000 ALTER TABLE `nguoisudung` DISABLE KEYS */;
INSERT INTO `nguoisudung` VALUES ('fffghhh','ffgfhh','Lãnh đạo'),('tuanle','12345','nhanvien');
/*!40000 ALTER TABLE `nguoisudung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhacungcap`
--

DROP TABLE IF EXISTS `nhacungcap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhacungcap` (
  `mancc` varchar(45) NOT NULL,
  `tenncc` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `sdt` varchar(45) NOT NULL,
  PRIMARY KEY (`mancc`),
  UNIQUE KEY `mancc_UNIQUE` (`mancc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhacungcap`
--

LOCK TABLES `nhacungcap` WRITE;
/*!40000 ALTER TABLE `nhacungcap` DISABLE KEYS */;
INSERT INTO `nhacungcap` VALUES ('www','wrr','hihi','ttrtr');
/*!40000 ALTER TABLE `nhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `manhanvien` varchar(45) NOT NULL,
  `hoten` varchar(45) NOT NULL,
  `CCCD` varchar(45) NOT NULL,
  `ngaycongtac` date NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`manhanvien`),
  UNIQUE KEY `MaNhanVien_UNIQUE` (`manhanvien`),
  UNIQUE KEY `CCCD_UNIQUE` (`CCCD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES ('20193167','Le ','1eeffw','2032-12-01','tuanvip@gamil.com'),('20193168','sssd','ddd','2023-01-12','dsdffff');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhasanxuat`
--

DROP TABLE IF EXISTS `nhasanxuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhasanxuat` (
  `mansx` varchar(45) NOT NULL,
  `tennsx` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `sdt` varchar(45) NOT NULL,
  PRIMARY KEY (`mansx`),
  UNIQUE KEY `mansx_UNIQUE` (`mansx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhasanxuat`
--

LOCK TABLES `nhasanxuat` WRITE;
/*!40000 ALTER TABLE `nhasanxuat` DISABLE KEYS */;
INSERT INTO `nhasanxuat` VALUES ('1','Tuanvip','Hà Nôi','0866589601'),('2','Tuanve','Hà tây','0000000');
/*!40000 ALTER TABLE `nhasanxuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieumuon`
--

DROP TABLE IF EXISTS `phieumuon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieumuon` (
  `maphieumuon` varchar(45) NOT NULL,
  `ngaylap` date NOT NULL,
  `ngaytra` date NOT NULL,
  `manguoimuon` varchar(45) NOT NULL,
  `manhanvien` varchar(45) NOT NULL,
  PRIMARY KEY (`maphieumuon`),
  UNIQUE KEY `maphieumuon_UNIQUE` (`maphieumuon`),
  KEY `phieumuon_nv_idx` (`manhanvien`),
  KEY `phieumuon_nguoimuon_idx` (`manguoimuon`),
  CONSTRAINT `phieumuon_nguoimuon` FOREIGN KEY (`manguoimuon`) REFERENCES `nguoimuon` (`manguoimuon`),
  CONSTRAINT `phieumuon_nhanvien` FOREIGN KEY (`manhanvien`) REFERENCES `nhanvien` (`manhanvien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieumuon`
--

LOCK TABLES `phieumuon` WRITE;
/*!40000 ALTER TABLE `phieumuon` DISABLE KEYS */;
INSERT INTO `phieumuon` VALUES ('1','2023-08-10','2023-01-23','2019','20193168');
/*!40000 ALTER TABLE `phieumuon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieunhap`
--

DROP TABLE IF EXISTS `phieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieunhap` (
  `maphieunhap` varchar(45) NOT NULL,
  `ngaynhap` date NOT NULL,
  `manhanvien` varchar(45) NOT NULL,
  `mancc` varchar(45) NOT NULL,
  PRIMARY KEY (`maphieunhap`),
  UNIQUE KEY `maphieunhap_UNIQUE` (`maphieunhap`),
  KEY `phieunhap_nhanvien_idx` (`manhanvien`),
  KEY `phieunhap_ncc_idx` (`mancc`),
  CONSTRAINT `phieunhap_ncc` FOREIGN KEY (`mancc`) REFERENCES `nhacungcap` (`mancc`),
  CONSTRAINT `phieunhap_nhanvien` FOREIGN KEY (`manhanvien`) REFERENCES `nhanvien` (`manhanvien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieunhap`
--

LOCK TABLES `phieunhap` WRITE;
/*!40000 ALTER TABLE `phieunhap` DISABLE KEYS */;
INSERT INTO `phieunhap` VALUES ('1','2023-01-23','20193167','www');
/*!40000 ALTER TABLE `phieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thietbi`
--

DROP TABLE IF EXISTS `thietbi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thietbi` (
  `matb` varchar(45) NOT NULL,
  `tentb` varchar(45) NOT NULL,
  `namsanxuat` varchar(45) NOT NULL,
  `mota` varchar(255) DEFAULT NULL,
  `tinhtrang` varchar(45) NOT NULL,
  `maloaitb` varchar(45) NOT NULL,
  `mansx` varchar(45) NOT NULL,
  `mavt` varchar(45) NOT NULL,
  `maphieunhap` varchar(45) NOT NULL,
  PRIMARY KEY (`matb`),
  UNIQUE KEY `mathietbi_UNIQUE` (`matb`),
  KEY `loaitb_thietbi_idx` (`maloaitb`),
  KEY `nsx_thietbi_idx` (`mansx`),
  KEY `vitri_thietbi_idx` (`mavt`),
  KEY `phieunhap_thietbi_idx` (`maphieunhap`),
  CONSTRAINT `loaitb_thietbi` FOREIGN KEY (`maloaitb`) REFERENCES `loaithietbi` (`maloaitb`),
  CONSTRAINT `nsx_thietbi` FOREIGN KEY (`mansx`) REFERENCES `nhasanxuat` (`mansx`),
  CONSTRAINT `phieunhap_thietbi` FOREIGN KEY (`maphieunhap`) REFERENCES `phieunhap` (`maphieunhap`),
  CONSTRAINT `vitri_thietbi` FOREIGN KEY (`mavt`) REFERENCES `vitri` (`mavt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thietbi`
--

LOCK TABLES `thietbi` WRITE;
/*!40000 ALTER TABLE `thietbi` DISABLE KEYS */;
INSERT INTO `thietbi` VALUES ('2','máy in','2021','Máy tin vip pro','tốt','1','2','D3','1'),('3','máy chiếu','2021','máy chiếu siêu cấp','Hỏng','1','2','D3','1');
/*!40000 ALTER TABLE `thietbi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vitri`
--

DROP TABLE IF EXISTS `vitri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vitri` (
  `mavt` varchar(45) NOT NULL,
  `tenvt` varchar(45) NOT NULL,
  `mota` varchar(255) NOT NULL,
  `manhanvien` varchar(45) NOT NULL,
  PRIMARY KEY (`mavt`),
  UNIQUE KEY `mavt_UNIQUE` (`mavt`),
  KEY `FK:vt_nv_idx` (`manhanvien`),
  CONSTRAINT `FK:vt_nv` FOREIGN KEY (`manhanvien`) REFERENCES `nhanvien` (`manhanvien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vitri`
--

LOCK TABLES `vitri` WRITE;
/*!40000 ALTER TABLE `vitri` DISABLE KEYS */;
INSERT INTO `vitri` VALUES ('D3','Giang duong D3','khu vuc hoc tap gan cog','20193167');
/*!40000 ALTER TABLE `vitri` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-11  0:14:32
