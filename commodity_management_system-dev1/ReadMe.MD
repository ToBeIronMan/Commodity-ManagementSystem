使用框架 spring boot
(如何可以用微服务的方式）

/*功能细节和要求*/

用户管理商品
超级用户 可以管理用户 赋予用户权限
超级用户需要手机短信验证
普通用户需要验证码验证
超级用户和普通的用户的权限检测
图像识别，自动识别货物信息
/*第一阶段目标*/
完成登陆注册和curd
任务分配
鄢意辉 登陆注册 
任务说明：要求带图像 数据库存入的是图片地址  图片存在D:/images文件下
可以从选取项目文件下image文件夹默认头像，也可以自定义头像
注册成功后，显示你已经注册成功，并停留三秒，然后跳转到注册界面
肖浩然  用户对商品的curd
 注意用户权限
张吉祥  超级用户对用户的curd
徐昌华  登陆注册界面（和鄢意辉交流） （主界面和肖浩然张吉祥交流）
大致说明，以实际交流为准
登陆界面说明 ： 两个输入框，（用户名和密码）两个按钮（登陆和注册），登陆为submit 注册是跳转
注册界面要求：用户的姓名，密码，电话 的三个文本框，和头像选择，注册和注册两个按钮 注册为submit 登陆是跳转
主界面 要求： 退出
             显示用户名和头像，
             有管理用户和增加商品两个label或者按钮或者logo，怎末好看怎末来
             点击增加商品弹出模态框
             点击管理用户，跳转到管理用户界面
             正中间文本框搜索
             并排显示图片，分页显示，下方显示商品详细信息，点击图片弹出修改模态框
 管理用户界面：退出
 表格显示所有用户的信息，再最后几横列，显示删除，修改，还有搜索文本框
             
/*第二阶段目标*/
待定
/*超级用户*/
create table super_user(
super_user_id bigint primary key, /超级用户id
super_user_name varchar(255) not null, /超级用户姓名
super_user_password varchar（255） not null, /密码
super_user_phone varchar(11)  not null，/手机用户号
super_user_head_image_adress varchar(255),/用户头像地址

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
