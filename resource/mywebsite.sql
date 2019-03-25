CREATE DATABASE  IF NOT EXISTS `mywebsite` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mywebsite`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: mywebsite
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `archive`
--

LOCK TABLES `archive` WRITE;
/*!40000 ALTER TABLE `archive` DISABLE KEYS */;
INSERT INTO `archive` VALUES (549,225,773),(552,225,777),(553,225,778),(554,225,779),(555,225,780),(556,225,781),(557,225,782),(560,227,773),(559,229,773),(562,229,777),(563,229,778),(564,229,779),(565,229,780),(566,229,781),(567,229,782);
/*!40000 ALTER TABLE `archive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `backend_user`
--

LOCK TABLES `backend_user` WRITE;
/*!40000 ALTER TABLE `backend_user` DISABLE KEYS */;
INSERT INTO `backend_user` VALUES (2,'zzy','1156846412',15,'admin'),(3,'guest','guest',0,'guest');
/*!40000 ALTER TABLE `backend_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (309,'今天发现了一个问题,就是通过代码来为Material设置法线贴图,但是设置后没有效果,需要在场景中来回切换一下才可以.如图所示:\n![](https://ws1.sinaimg.cn/large/006nw0Eagy1g0yw3fu6qkg30yh0mw486.gif)\n\n这是我Test里面的代码:\n```csharp \n[MenuItem(\"Test/Test\")]\npublic static void Test()\n{\n    Texture texture = Resources.Load<Texture>(\"BMW.fbm/luntai_Low_Luntai_Normal\");\n    Material material = Resources.Load<Material>(\"Materials/luntai_Low_Luntai_AlbedoTransparency\");\n    material.SetTexture(\"_BumpMap\", texture);\n}\n```\n\n没什么特别的,就是将Texture和Material加载出来并且将Texture设置为Material的法线贴图!不管怎么刷新AssetDatabase.Refresh或者导入AssetsSetting.ImportAsset都没用,最后在官网找到解决方案!\n\n需要使用EnableKeyword方法来为Standard Shader启用正确的关键字!例如对于设置法线贴图而言只需要添加一行代码即可:\n```csharp\n[MenuItem(\"Test/Test\")]\npublic static void Test()\n{\n    Texture texture = Resources.Load<Texture>(\"BMW.fbm/luntai_Low_Luntai_Normal\");\n    Material material = Resources.Load<Material>(\"Materials/luntai_Low_Luntai_AlbedoTransparency\");\n    material.SetTexture(\"_BumpMap\", texture);\n    material.EnableKeyword(\"_NORMALMAP\");\n}\n```\n\n关于该问题的更多详细信息,请查看官网文档:https://docs.unity3d.com/Manual/MaterialsAccessingViaScript.html'),(310,'hahahah'),(311,'hahahah'),(312,'hahahah'),(313,'hahahah');
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `blog_info`
--

LOCK TABLES `blog_info` WRITE;
/*!40000 ALTER TABLE `blog_info` DISABLE KEYS */;
INSERT INTO `blog_info` VALUES (225,'Unity在代码中为Material设置Texture,场景中没有更新Texture,加长表踢踢踢踢踢踢踢踢','2019-03-11 15:42:11',15,920,309,'2019-03-23 18:01:59',0,'今天发现了一个问题,就是通过代码来为Material设置法线贴图,但是设置后没有效果,需要在场景中来回切换一下才可以.如图所示:\n这是我Test里面的代码:\n[MenuItem(\"Test/Test\"',1236),(226,'测试','2019-03-22 17:09:04',0,7,310,'2019-03-22 17:09:04',0,'hahahah',1249),(227,'测试','2019-03-22 17:09:07',0,7,311,'2019-03-22 17:09:07',0,'hahahah',1250),(228,'测试','2019-03-22 17:09:09',0,7,312,'2019-03-22 17:09:09',0,'hahahah',1251),(229,'测试','2019-03-22 17:09:11',0,7,313,'2019-03-22 17:56:42',0,'hahahah',1252);
/*!40000 ALTER TABLE `blog_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (110,'太多空白格',-1,'2019-03-11 10:28:18',308,'00d6eb5050c4c14c53ebc8f81d79947f',0),(111,'是的',110,'2019-03-11 14:09:58',308,'00d6eb5050c4c14c53ebc8f81d79947f',0),(112,'打算',-1,'2019-03-11 17:31:43',309,'3b50bb3b4ee0434899dbcd0f7420f6b1',0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `img`
--

LOCK TABLES `img` WRITE;
/*!40000 ALTER TABLE `img` DISABLE KEYS */;
INSERT INTO `img` VALUES (1231,NULL,NULL,NULL),(1236,'https://ws1.sinaimg.cn/thumbnail/006nw0Eagy1g0yw9rthufj31hc0u0n1y.jpg','https://ws1.sinaimg.cn/mw690/006nw0Eagy1g0yw9rthufj31hc0u0n1y.jpg','https://ws1.sinaimg.cn/large/006nw0Eagy1g0yw9rthufj31hc0u0n1y.jpg'),(1237,'https://cdn6.aptoide.com/imgs/2/7/7/2776b78b928ebc67593d86e4e3323684_icon.png?w=240',NULL,NULL),(1239,NULL,NULL,NULL),(1243,NULL,NULL,NULL),(1244,NULL,NULL,NULL),(1245,NULL,NULL,NULL),(1246,NULL,NULL,NULL),(1247,NULL,NULL,NULL),(1248,NULL,NULL,NULL),(1249,NULL,NULL,NULL),(1250,NULL,NULL,NULL),(1251,NULL,NULL,NULL),(1252,NULL,NULL,NULL),(1288,NULL,NULL,NULL);
/*!40000 ALTER TABLE `img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (773,'Unity',1237),(777,'中文',1243),(778,'电影',1244),(779,'电视剧',1245),(780,'小说',1246),(781,'音乐',1247),(782,'java',1248);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('00d6eb5050c4c14c53ebc8f81d79947f','空白格',1231,NULL),('12345678901234567890123456789012','name update test',1288,'email update'),('3b50bb3b4ee0434899dbcd0f7420f6b1','空白格',1239,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mywebsite'
--

--
-- Dumping routines for database 'mywebsite'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-25 15:59:26
