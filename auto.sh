#自动部署脚本
#!/bin/bash
cd ~
dirName="myccnu"
serverWebPath="/opt/jetty/webapps/"
if [ -e "$dirName" ]
then
  echo "$dirName已经存在,删除它"
  rm -rf "$dirName"
fi
echo "创建文件夹$dirName"
mkdir "$dirName"
echo "从github上下载源代码"
git clone https://github.com/gwuhaolin/myccnu.git
echo "下载完成"
cd "$dirName"
echo "开始编译代码"
gradle war
echo "重新部署项目"
cp -f ROOT.war ${serverWebPath}"ROOT.war"
echo "重新启动服务器"
/opt/jetty/bin/jetty.sh restart
