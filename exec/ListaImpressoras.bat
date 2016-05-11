@echo off
set SERVICEMIX_HOME=..
set CLASSPATH=%SERVICEMIX_HOME%/conf;%SERVICEMIX_HOME%/nfe/lib;%SERVICEMIX_HOME%/nfe/lib-ext;%SERVICEMIX_HOME%/nfe/wsdl;%SERVICEMIX_HOME%/nfe/resources;%SERVICEMIX_HOME%/lib/classworlds-1.0.1.jar;%SERVICEMIX_HOME%/nfe/lib/nfe-printer-2.15.jar
rem echo %SERVICEMIX_HOME%
rem echo %CLASSPATH%
rem	exec $JAVA \
rem         $JAVA_OPTS \
rem         $SERVICEMIX_OPTS \
rem         -classpath "$CLASSPATH" \
rem         -Dclassworlds.conf="$CLASSWORLDS_CONF" \
rem         -Dservicemix.home="$SERVICEMIX_HOME" \
rem         -Dcygwin.user.home="$CYGHOME" \
rem         -Djava.endorsed.dirs="$SERVICEMIX_HOME/lib/endorsed" \
rem         br.inf.gati.nfe.componentes.printer.ListarImpressoras \
rem         "$@"    
echo java -classpath %CLASSPATH% br.inf.gati.nfe.componentes.printer.ListarImpressoras
java -classpath %CLASSPATH% br.inf.gati.nfe.componentes.printer.ListarImpressoras


Pause