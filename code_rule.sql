/*
 Navicat Premium Data Transfer

 Source Server         : 101.35.211.97_7213
 Source Server Type    : MySQL
 Source Server Version : 80039
 Source Host           : 101.35.211.97:7213
 Source Schema         : yunyan_hr_dev

 Target Server Type    : MySQL
 Target Server Version : 80039
 File Encoding         : 65001

 Date: 27/04/2025 11:12:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for code_rule
-- ----------------------------
DROP TABLE IF EXISTS `code_rule`;
CREATE TABLE `code_rule`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '编码适用类型：员工',
  `code_formula` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '编码公式',
  `code_count` int NULL DEFAULT NULL COMMENT '编码计数',
  `step_size` int NULL DEFAULT NULL COMMENT '每次步长',
  `remarks` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_deactivate` tinyint NULL DEFAULT NULL COMMENT '是否停用',
  `CREATOR` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `UPDATOR` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `CREATED` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '编码生成规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of code_rule
-- ----------------------------
INSERT INTO `code_rule` VALUES (1, 'employee', 'TEST-$递增编号5位$', 54, 1, NULL, 0, NULL, '1', NULL, '2025-03-20 09:49:16');
INSERT INTO `code_rule` VALUES (2, 'employee', 'HR-$年$$递增编号6位$', 3, 1, NULL, 1, '1', '1', '2024-09-03 10:31:35', '2024-09-03 10:31:45');
INSERT INTO `code_rule` VALUES (3, '123', NULL, 123, 1, NULL, 1, '1', NULL, '2024-11-08 15:11:39', NULL);
INSERT INTO `code_rule` VALUES (4, '123', NULL, 123, 1, NULL, 1, '1', NULL, '2024-11-08 15:15:48', NULL);

SET FOREIGN_KEY_CHECKS = 1;
