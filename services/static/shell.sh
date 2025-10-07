# 仅记录项目中使用到的命令

# 创建网络
docker network create nacos-mysql-network
# nacos依赖mysql，先启动mysql
docker run -d --name mysql   --network nacos-mysql-network \
-e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=nacos -p 3306:3306   mysql:8.0.37-debian
# docker安装nacos并启动
docker run -d --name nacos \
  -e MODE=standalone \
  --network nacos-mysql-network \
  -e MYSQL_HOST=mysql \
  -e MYSQL_PORT=3306 \
  -e MYSQL_USER=root \
  -e MYSQL_PASSWORD=root \
  -e MYSQL_DATABASE=nacos \
  -p 8848:8848 \
  -p 9848:9848 \
  -p 9849:9849 \
  nacos/nacos-server:v2.4.3

  # 安装mysql
docker run --name mysql-container \
  -e MYSQL_ROOT_PASSWORD=root \
  -p 3306:3306 \
  -v /path/to/local/mysql-data:/var/lib/mysql \
  -d mysql:8.0.37-debian