# Commodity-ManagementSystem
使用框架 spring boot
(如何可以用微服务的方式）
用户管理商品
超级用户 可以管理用户 赋予用户权限

超级用户需要手机短信验证
普通用户需要验证码验证
超级用户和普通的用户的权限检测
图像识别，自动识别货物信息

/*超级用户*/
create table super_user(
super_user_id bigint primary key, /超级用户id
super_user_password varchar（255） not null, /密码
)
/*用户权限集合*/
create user_permission(
id  bigint primary key,/id
user_id bigint not null,/外键，用户表的用户编号
add_permission int ,/添加权限
delete_permission int,/删除权限
update_permission int /更新权限
)

/*用户*/
create table user(
user_id bigint primary key, /用户编号
user_name varchar(255) not null, /用户姓名
user_password varchar(255) not null,/用户密码
user_phone varchar(11)  not null，/手机用户号
user_head_image_adress varchar(255),/用户头像地址
)

/*商品*/
create table commodity(
number bigint primary key not null,/编号
name varchar(255)  not null,/名称
type varchar,/商品类型
price double ,/价格
quantity  bigint ，/数量
image_adress varchar(255)/图片保存位置
）
