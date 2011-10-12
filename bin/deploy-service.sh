GFPATH='/Users/jecoble/glassfish3.1.1'
TARGETPATH='/Users/jecoble/Documents/workspace'

sh $GFPATH/glassfish/bin/asadmin undeploy GraphManagerService-ear
sh $GFPATH/glassfish/bin/asadmin deploy $TARGETPATH/GraphManagerService/GraphManagerService-ear/target/GraphManagerService-ear.ear
