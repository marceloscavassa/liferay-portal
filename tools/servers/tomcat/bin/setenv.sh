CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF-8 -Djava.locale.providers=JRE,COMPAT,CLDR -Djava.net.preferIPv4Stack=true -Djdk.util.zip.disableZip64ExtraFieldValidation=true -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxNewSize=1536m -XX:MaxMetaspaceSize=768m -XX:MetaspaceSize=768m -XX:NewSize=1536m -XX:SurvivorRatio=7"

JDK_JAVA_OPTIONS="$JDK_JAVA_OPTIONS --add-opens=jdk.zipfs/jdk.nio.zipfs=ALL-UNNAMED"