set Axis_Lib=D:\svn\c\axis-bin-1_4\axis-1_4\lib
set Java_Cmd=java -Djava.ext.dirs=%Axis_Lib%
%Java_Cmd% org.apache.axis.wsdl.WSDL2Java   -p cn.backend.service.wsdl.caseback D:\svn\c\axis-bin-1_4\ChargingPileInfoFollowUpWebservice.wsdl
pause