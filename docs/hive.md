问题：配置好 hive 启动 server2 后虽然启动成功但是无法连接

原因：没有在 hadoop-core.xml 中配置
```xml
<property>
    <name>hadoop.proxyuser.xxx.hosts</name>
    <value>*</value>
</property>
<property>
    <name>hadoop.proxyuser.xxx.groups</name>
    <value>*</value>
</property>
```

问题：配置好 hadoop-core.xml 后启动还是无法连接

原因：未提前启动 hive --service metasotre


解决完上面两个问题就可以在 IDEA 中连接 Hive server2 了.