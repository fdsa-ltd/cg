<#noparse>
nohup java -Xms512M -Xmx2048M -XX:MaxNewSize=1024M -XX:PermSize=64M -XX:MaxPermSize=1024M -jar platform.jar > ./platform.log 2>&1 &
</#noparse>