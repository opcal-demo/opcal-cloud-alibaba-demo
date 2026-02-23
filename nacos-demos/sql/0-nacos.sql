
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `config_info`
--

DROP TABLE IF EXISTS `config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `data_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'data_id',
                               `group_id` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'group_id',
                               `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'content',
                               `md5` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'md5',
                               `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                               `src_user` text COLLATE utf8mb4_unicode_ci COMMENT 'source user',
                               `src_ip` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'source ip',
                               `app_name` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'app_name',
                               `tenant_id` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '租户字段',
                               `c_desc` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'configuration description',
                               `c_use` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'configuration usage',
                               `effect` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '配置生效的描述',
                               `type` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '配置的类型',
                               `c_schema` text COLLATE utf8mb4_unicode_ci COMMENT '配置的模式',
                               `encrypted_data_key` varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密钥',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='config_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info`
--

LOCK TABLES `config_info` WRITE;
/*!40000 ALTER TABLE `config_info` DISABLE KEYS */;
INSERT INTO `config_info` VALUES (1,'nd-service-a','DEFAULT_GROUP','nd:\n  a:\n    calculator:\n      x-factor: 0.987','809562b3ba6e82410358b9d7f403be26','2026-02-23 10:19:53','2026-02-23 10:19:53','nacos','192.168.65.1','','opcal-cloud-alibaba-demo',NULL,NULL,NULL,'yaml',NULL,''),(2,'nd-service-a-flow-rule','SENTINEL_GROUP','[\n  {\n    \"resource\": \"POST:http://nd-service-b/original-api/calculate\",\n    \"controlBehavior\": 0,\n    \"count\": 50,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0\n  }\n]','80c5a1463f280519a2a3a9102e3afeb3','2026-02-23 10:19:53','2026-02-23 10:19:53','nacos','192.168.65.1','','opcal-cloud-alibaba-demo',NULL,NULL,NULL,'json',NULL,''),(3,'nd-service-a-degrade-rule','SENTINEL_GROUP','[\n    {\n        \"resource\": \"xyz.opcal.demo.cloud.nd.api.service.CalculatorService:calculate(double,double,double)\",\n        \"count\": 2.5,\n        \"grade\": 0,\n        \"timeWindow\": 2\n    }\n]','d0edbf44fc5fc3d5ffaa20e6c9bf7905','2026-02-23 10:19:53','2026-02-23 10:19:53','nacos','192.168.65.1','','opcal-cloud-alibaba-demo',NULL,NULL,NULL,'json',NULL,''),(4,'nd-service-b','DEFAULT_GROUP','nd:\n  b:\n    calculator:\n      x-factor: 0.987\n      y-factor: 10.314\n      z-factor: 20.159\n','d174a7a96cd5be6d3950ab485ae3ae28','2026-02-23 10:19:53','2026-02-23 10:19:53','nacos','192.168.65.1','','opcal-cloud-alibaba-demo',NULL,NULL,NULL,'yaml',NULL,''),(5,'nd-service-c-flow-rule','SENTINEL_GROUP','[\n\n  {\n    \"resource\": \"POST:http://nd-service-a/original-api/calculate\",\n    \"controlBehavior\": 0,\n    \"count\": 150,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0\n  },\n  {\n    \"resource\": \"POST:http://nd-service-b/original-api/calculate\",\n    \"controlBehavior\": 0,\n    \"count\": 250,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0\n  }\n]','94bbb08c493ef35179809b58d68415f7','2026-02-23 10:19:53','2026-02-23 10:19:53','nacos','192.168.65.1','','opcal-cloud-alibaba-demo','',NULL,NULL,'json',NULL,''),(6,'nd-service-c-degrade-rule','SENTINEL_GROUP','[\n  {\n    \"resource\": \"xyz.opcal.demo.cloud.nd.api.service.CalculatorService:calculate(double,double,double)\",\n    \"count\": 25,\n    \"grade\": 0,\n    \"timeWindow\": 1,\n    \"minRequestAmount\": 25,\n    \"slowRatioThreshold\": 0.6\n  }\n]','bb7496167a4ac3597e4566c208dfe77b','2026-02-23 10:19:53','2026-02-23 10:19:53','nacos','192.168.65.1','','opcal-cloud-alibaba-demo','',NULL,NULL,'json',NULL,'');
/*!40000 ALTER TABLE `config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_gray`
--

DROP TABLE IF EXISTS `config_info_gray`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info_gray` (
                                    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                    `data_id` varchar(255) NOT NULL COMMENT 'data_id',
                                    `group_id` varchar(128) NOT NULL COMMENT 'group_id',
                                    `content` longtext NOT NULL COMMENT 'content',
                                    `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
                                    `src_user` text COMMENT 'src_user',
                                    `src_ip` varchar(100) DEFAULT NULL COMMENT 'src_ip',
                                    `gmt_create` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT 'gmt_create',
                                    `gmt_modified` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT 'gmt_modified',
                                    `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
                                    `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
                                    `gray_name` varchar(128) NOT NULL COMMENT 'gray_name',
                                    `gray_rule` text NOT NULL COMMENT 'gray_rule',
                                    `encrypted_data_key` varchar(256) NOT NULL DEFAULT '' COMMENT 'encrypted_data_key',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `uk_configinfogray_datagrouptenantgray` (`data_id`,`group_id`,`tenant_id`,`gray_name`),
                                    KEY `idx_dataid_gmt_modified` (`data_id`,`gmt_modified`),
                                    KEY `idx_gmt_modified` (`gmt_modified`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='config_info_gray';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_gray`
--

LOCK TABLES `config_info_gray` WRITE;
/*!40000 ALTER TABLE `config_info_gray` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_gray` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_tags_relation`
--

DROP TABLE IF EXISTS `config_tags_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_tags_relation` (
                                        `id` bigint NOT NULL COMMENT 'id',
                                        `tag_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'tag_name',
                                        `tag_type` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'tag_type',
                                        `data_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'data_id',
                                        `group_id` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'group_id',
                                        `tenant_id` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'tenant_id',
                                        `nid` bigint NOT NULL AUTO_INCREMENT COMMENT 'nid, 自增长标识',
                                        PRIMARY KEY (`nid`),
                                        UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
                                        KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='config_tag_relation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_tags_relation`
--

LOCK TABLES `config_tags_relation` WRITE;
/*!40000 ALTER TABLE `config_tags_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_tags_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_capacity`
--

DROP TABLE IF EXISTS `group_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_capacity` (
                                  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  `group_id` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
                                  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
                                  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
                                  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
                                  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
                                  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
                                  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
                                  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='集群、各Group容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_capacity`
--

LOCK TABLES `group_capacity` WRITE;
/*!40000 ALTER TABLE `group_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `his_config_info`
--

DROP TABLE IF EXISTS `his_config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `his_config_info` (
                                   `id` bigint unsigned NOT NULL COMMENT 'id',
                                   `nid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'nid, 自增标识',
                                   `data_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'data_id',
                                   `group_id` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'group_id',
                                   `app_name` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'app_name',
                                   `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'content',
                                   `md5` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'md5',
                                   `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                   `src_user` text COLLATE utf8mb4_unicode_ci COMMENT 'source user',
                                   `src_ip` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'source ip',
                                   `op_type` char(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'operation type',
                                   `tenant_id` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '租户字段',
                                   `encrypted_data_key` varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密钥',
                                   `publish_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'formal' COMMENT 'publish type gray or formal',
                                   `gray_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'gray name',
                                   `ext_info` longtext COLLATE utf8mb4_unicode_ci COMMENT 'ext info',
                                   PRIMARY KEY (`nid`),
                                   KEY `idx_gmt_create` (`gmt_create`),
                                   KEY `idx_gmt_modified` (`gmt_modified`),
                                   KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='多租户改造';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `his_config_info`
--

LOCK TABLES `his_config_info` WRITE;
/*!40000 ALTER TABLE `his_config_info` DISABLE KEYS */;
INSERT INTO `his_config_info` VALUES (0,1,'nd-service-a','DEFAULT_GROUP','','nd:\n  a:\n    calculator:\n      x-factor: 0.987','809562b3ba6e82410358b9d7f403be26','2026-02-23 10:19:53','2026-02-23 18:19:53','nacos','192.168.65.1','I','opcal-cloud-alibaba-demo','','formal','','{\"src_user\":\"nacos\",\"type\":\"yaml\"}'),(0,2,'nd-service-a-flow-rule','SENTINEL_GROUP','','[\n  {\n    \"resource\": \"POST:http://nd-service-b/original-api/calculate\",\n    \"controlBehavior\": 0,\n    \"count\": 50,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0\n  }\n]','80c5a1463f280519a2a3a9102e3afeb3','2026-02-23 10:19:53','2026-02-23 18:19:53','nacos','192.168.65.1','I','opcal-cloud-alibaba-demo','','formal','','{\"src_user\":\"nacos\",\"type\":\"json\"}'),(0,3,'nd-service-a-degrade-rule','SENTINEL_GROUP','','[\n    {\n        \"resource\": \"xyz.opcal.demo.cloud.nd.api.service.CalculatorService:calculate(double,double,double)\",\n        \"count\": 2.5,\n        \"grade\": 0,\n        \"timeWindow\": 2\n    }\n]','d0edbf44fc5fc3d5ffaa20e6c9bf7905','2026-02-23 10:19:53','2026-02-23 18:19:53','nacos','192.168.65.1','I','opcal-cloud-alibaba-demo','','formal','','{\"src_user\":\"nacos\",\"type\":\"json\"}'),(0,4,'nd-service-b','DEFAULT_GROUP','','nd:\n  b:\n    calculator:\n      x-factor: 0.987\n      y-factor: 10.314\n      z-factor: 20.159\n','d174a7a96cd5be6d3950ab485ae3ae28','2026-02-23 10:19:53','2026-02-23 18:19:53','nacos','192.168.65.1','I','opcal-cloud-alibaba-demo','','formal','','{\"src_user\":\"nacos\",\"type\":\"yaml\"}'),(0,5,'nd-service-c-flow-rule','SENTINEL_GROUP','','[\n\n  {\n    \"resource\": \"POST:http://nd-service-a/original-api/calculate\",\n    \"controlBehavior\": 0,\n    \"count\": 150,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0\n  },\n  {\n    \"resource\": \"POST:http://nd-service-b/original-api/calculate\",\n    \"controlBehavior\": 0,\n    \"count\": 250,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0\n  }\n]','94bbb08c493ef35179809b58d68415f7','2026-02-23 10:19:53','2026-02-23 18:19:53','nacos','192.168.65.1','I','opcal-cloud-alibaba-demo','','formal','','{\"src_user\":\"nacos\",\"type\":\"json\"}'),(0,6,'nd-service-c-degrade-rule','SENTINEL_GROUP','','[\n  {\n    \"resource\": \"xyz.opcal.demo.cloud.nd.api.service.CalculatorService:calculate(double,double,double)\",\n    \"count\": 25,\n    \"grade\": 0,\n    \"timeWindow\": 1,\n    \"minRequestAmount\": 25,\n    \"slowRatioThreshold\": 0.6\n  }\n]','bb7496167a4ac3597e4566c208dfe77b','2026-02-23 10:19:53','2026-02-23 18:19:53','nacos','192.168.65.1','I','opcal-cloud-alibaba-demo','','formal','','{\"src_user\":\"nacos\",\"type\":\"json\"}');
/*!40000 ALTER TABLE `his_config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
                               `role` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'role',
                               `resource` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'resource',
                               `action` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'action',
                               UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES ('demo','metadata:*:*','rw'),('demo','opcal-cloud-alibaba-demo:*:*','rw'),('demo','public:*:*','rw');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
                         `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'username',
                         `role` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'role',
                         UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('demo','demo'),('nacos','ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_capacity`
--

DROP TABLE IF EXISTS `tenant_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_capacity` (
                                   `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                   `tenant_id` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'Tenant ID',
                                   `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
                                   `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
                                   `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
                                   `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
                                   `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
                                   `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
                                   `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='租户容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_capacity`
--

LOCK TABLES `tenant_capacity` WRITE;
/*!40000 ALTER TABLE `tenant_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_info`
--

DROP TABLE IF EXISTS `tenant_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_info` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `kp` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'kp',
                               `tenant_id` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'tenant_id',
                               `tenant_name` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'tenant_name',
                               `tenant_desc` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'tenant_desc',
                               `create_source` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'create_source',
                               `gmt_create` bigint NOT NULL COMMENT '创建时间',
                               `gmt_modified` bigint NOT NULL COMMENT '修改时间',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
                               KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='tenant_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_info`
--

LOCK TABLES `tenant_info` WRITE;
/*!40000 ALTER TABLE `tenant_info` DISABLE KEYS */;
INSERT INTO `tenant_info` VALUES (1,'1','metadata','metadata','metadata','nacos',1771841819265,1771841819265),(2,'1','opcal-cloud-alibaba-demo','opcal-cloud-alibaba-demo','opcal-cloud-alibaba-demo','nacos',1771841833102,1771841833102);
/*!40000 ALTER TABLE `tenant_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'username',
                         `password` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'password',
                         `enabled` tinyint(1) NOT NULL COMMENT 'enabled',
                         PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('demo','$2a$10$v2YlVJMlDRi5VWgqt6l4C.Y1f82.H9nhf4wfoh/Vcm0bvTRkju6yK',1),('nacos','$2a$10$4it0a5kzulppuAMDxmfsP.Ld7l.8UgjL7hQZPajMzTmISBVD2tatW',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;