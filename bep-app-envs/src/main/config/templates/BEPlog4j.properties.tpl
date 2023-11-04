### logger configuration
### 07/10/2013 7:15am

log4j.rootLogger=ERROR, root
log4j.logger.gov.va.vba.framework=@default_log_level@, framework
log4j.logger.gov.va.vba.focas=@default_log_level@, focas
log4j.logger.gov.va.vba.wsms=@default_log_level@, wsms
log4j.logger.gov.va.vba.weams=@default_log_level@, weams
log4j.logger.gov.va.vba.corp=ERROR, corpFileAppender
log4j.logger.gov.va.vba.corp.common=@default_log_level@, common 
log4j.logger.gov.va.vba.corp.webservice.commom=@default_log_level@, common 
log4j.logger.gov.va.vba.corp.ro=@default_log_level@,ro
log4j.logger.gov.va.vba.corp.webservice.ro=@default_log_level@,ro
log4j.logger.gov.va.vba.rbps=@default_log_level@,rbps
log4j.logger.gov.va.vis=@default_log_level@,vis
log4j.logger.gov.va.cps=@default_log_level@,cps
log4j.logger.gov.va.benefits.cps=@default_log_level@,cps
log4j.logger.gov.va.vba.framework.esb.proxy.handler.dao.impl.CssProfileCacheDAO_Impl=INFO,ecache  
log4j.logger.gov.vba.vre.qa.web=@default_log_level@,vreqa
log4j.logger.gov.vba.vreqa=@default_log_level@,vreqa

log4j.logger.gov.va.vba.corp.person=@default_log_level@,person
log4j.logger.gov.va.vba.corp.webservice.person=@default_log_level@,person

#EDU will be deployed in MAY
log4j.logger.gov.va.vba.corp.edu=@default_log_level@,edu
log4j.logger.gov.va.vba.corp.webservice.edu=@default_log_level@,edu

#vetsnet
log4j.logger.gov.va.vba.benefits.covers=@default_log_level@,vetsnet
log4j.logger.gov.va.vba.benefits.share=@default_log_level@,vetsnet
log4j.logger.gov.va.birls=@default_log_level@,vetsnet
log4j.logger.gov.va.vba.corporate=@default_log_level@,vetsnet
log4j.logger.gov.va.vba.ebenefits=@default_log_level@,vetsnet
log4j.logger.gov.va.vba.stat=@default_log_level@,vetsnet
log4j.logger.gov.va.vba.vetsnet=@default_log_level@,vetsnet
log4j.logger.gov.va.vba.vetsnet.fts=@default_log_level@,fts

#more from vetsnet 10/7/2011
log4j.logger.gov.va.vba.benefits.arch=@default_log_level@,VBA
log4j.logger.gov.va.vba.benefits.arch.wtc=INFO,VBA 
log4j.logger.gov.va.vba.benefits.awards=@default_log_level@,VBA
log4j.logger.gov.va.vba.benefits.ar=@default_log_level@,VBA
log4j.logger.gov.va.vba.benefits.common=@default_log_level@,VBA
log4j.logger.gov.va.vba.benefits.cp=@default_log_level@,VBA
log4j.logger.gov.va.vba.benefits.fa=@default_log_level@,VBA
# more from vetsnet 10/28/2011 -remove add below with diff appenders... 
# log4j.logger.gov.va.vba.benefits.mapd=@default_log_level@,VBA
# log4j.logger.gov.va.vba.benefits.rba=@default_log_level@,VBA
log4j.logger.gov.va.vba.benefits.vn=@default_log_level@,VBA
log4j.logger.gov.va.vba.benefits.servicelogic=@default_log_level@,VBA
log4j.logger.gov.va.vba.benefits.webserviceclients=@default_log_level@,VBA
log4j.logger.gov.va.vba.benefits.cs=@default_log_level@,VBA
log4j.logger.org.dozer=error,VBA
log4j.logger.org.hibernate=error,VBA
log4j.logger.javax.validation=error,VBA
log4j.logger.org.apache=error,VBA
#more from vetsnet 10/28/2011
log4j.logger.gov.va.vba.benefits.mapd=@default_log_level@,MAPD
log4j.logger.gov.va.vba.benefits.rba=@default_log_level@,RBA
log4j.logger.gov.va.vba.benefits.jdbc=@default_log_level@,VBMSHARED
log4j.logger.gov.va.vba.benefits.utilities=@default_log_level@,VBMSHARED
log4j.logger.gov.va.vba.benefits.vo=@default_log_level@,VBMSHARED
#vonapp
log4j.logger.gov.va.vba.benefits.vdc=@default_log_level@,vonapp
log4j.logger.gov.va.vba.vonapp=@default_log_level@,vonapp

### audit configuration
log4j.logger.audit=INFO, rootaudit


### LOGGING appenders configuration

log4j.appender.root = org.apache.log4j.RollingFileAppender
log4j.appender.root.File = @log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/root.log
log4j.appender.root.MaxFileSize = 250000KB
log4j.appender.root.MaxBackupIndex=20
log4j.appender.root.Append = true
log4j.appender.root.layout = org.apache.log4j.PatternLayout
log4j.appender.root.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss,SSS} %c{1} [%p] %m%n

log4j.appender.vreqa = org.apache.log4j.RollingFileAppender
log4j.appender.vreqa.File = @log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/vreqa.log
log4j.appender.vreqa.MaxFileSize = 250000KB
log4j.appender.vreqa.MaxBackupIndex=20
log4j.appender.vreqa.Append = true
log4j.appender.vreqa.layout = org.apache.log4j.PatternLayout
log4j.appender.vreqa.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss,SSS} %c{1} [%p] %m%n

log4j.appender.cps = org.apache.log4j.RollingFileAppender
log4j.appender.cps.File = @log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/cps.log
log4j.appender.cps.MaxFileSize = 250000KB
log4j.appender.cps.MaxBackupIndex=20
log4j.appender.cps.Append = true
log4j.appender.cps.layout = org.apache.log4j.PatternLayout
log4j.appender.cps.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss,SSS} %c{1} [%p] %m%n

log4j.appender.ecache = org.apache.log4j.RollingFileAppender
log4j.appender.ecache.File = @log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/ecache.log
log4j.appender.ecache.MaxFileSize = 250000KB
log4j.appender.ecache.MaxBackupIndex=20
log4j.appender.ecache.Append = true
log4j.appender.ecache.layout = org.apache.log4j.PatternLayout
log4j.appender.ecache.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss,SSS} %c{1} [%p] %m%n

log4j.appender.corpFileAppender=org.apache.log4j.RollingFileAppender 
log4j.appender.corpFileAppender.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/corp.log 
log4j.appender.corpFileAppender.MaxFileSize=250000KB 
log4j.appender.corpFileAppender.MaxBackupIndex=20
log4j.appender.corpFileAppender.Append = true
log4j.appender.corpFileAppender.layout=org.apache.log4j.PatternLayout 
log4j.appender.corpFileAppender.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %c{1} [%p] %m%n
log4j.additivity.gov.va.vba.corp=false

log4j.appender.common=org.apache.log4j.RollingFileAppender 
log4j.appender.common.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/corp_common.log 
log4j.appender.common.MaxFileSize=250000KB 
log4j.appender.common.MaxBackupIndex=20
log4j.appender.common.Append = true
log4j.appender.common.layout=org.apache.log4j.PatternLayout 
log4j.appender.common.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %c{1} [%p] %m%n
log4j.additivity.gov.va.vba.corp.common=false
log4j.additivity.gov.va.vba.corp.webservice.common=false

log4j.appender.framework = org.apache.log4j.RollingFileAppender
log4j.appender.framework.File = @log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/framework.log
log4j.appender.framework.MaxFileSize = 250000KB
log4j.appender.framework.MaxBackupIndex=20
log4j.appender.framework.Append = true
log4j.appender.framework.layout = org.apache.log4j.PatternLayout
log4j.appender.framework.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss,SSS} %c{1} [%p] %m%n
log4j.additivity.gov.va.vba.framework=false

log4j.appender.focas = org.apache.log4j.RollingFileAppender
log4j.appender.focas.File = @log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/focas.log
log4j.appender.focas.MaxFileSize = 250000KB
log4j.appender.focas.MaxBackupIndex=20
log4j.appender.focas.Append = true
log4j.appender.focas.layout = org.apache.log4j.PatternLayout
log4j.appender.focas.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss,SSS} %c{1} [%p] %m%n
log4j.additivity.gov.va.vba.focas=false

log4j.appender.wsms = org.apache.log4j.RollingFileAppender
log4j.appender.wsms.File = @log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/wsms.log
log4j.appender.wsms.MaxFileSize = 250000KB
log4j.appender.wsms.MaxBackupIndex=20
log4j.appender.wsms.Append = true
log4j.appender.wsms.layout = org.apache.log4j.PatternLayout
log4j.appender.wsms.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss,SSS} %c{1} [%p] %m%n
log4j.additivity.gov.va.vba.wsms=false

log4j.appender.weams = org.apache.log4j.RollingFileAppender
log4j.appender.weams.File = @log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/weams.log
log4j.appender.weams.MaxFileSize = 250000KB
log4j.appender.weams.MaxBackupIndex=20
log4j.appender.weams.layout = org.apache.log4j.PatternLayout
log4j.appender.weams.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss,SSS} %c{1} [%p] %m%n
log4j.additivity.gov.va.vba.weams=false

log4j.appender.ro=org.apache.log4j.RollingFileAppender 
log4j.appender.ro.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/corp_ro.log 
log4j.appender.ro.MaxFileSize=250000KB 
log4j.appender.ro.MaxBackupIndex=20
log4j.appender.ro.Append = true
log4j.appender.ro.layout=org.apache.log4j.PatternLayout 
log4j.appender.ro.layout.ConversionPattern=%d{MM-dd-yyyy hh:mm:ss} %p [%c.%M()]:%m%x%n
log4j.additivity.gov.va.vba.corp.ro=false
log4j.additivity.gov.va.vba.corp.webservice.ro=false

log4j.appender.edu=org.apache.log4j.RollingFileAppender 
log4j.appender.edu.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/corp_edu.log 
log4j.appender.edu.MaxFileSize=250000KB 
log4j.appender.edu.MaxBackupIndex=20
log4j.appender.edu.Append = true
log4j.appender.edu.layout=org.apache.log4j.PatternLayout 
log4j.appender.edu.layout.ConversionPattern=%d{MM-dd-yyyy hh:mm:ss} %p [%c.%M()]:%m%x%n
log4j.additivity.gov.va.vba.corp.edu=false
log4j.additivity.gov.va.vba.corp.webservice.edu=false

log4j.appender.person=org.apache.log4j.RollingFileAppender 
log4j.appender.person.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/corp_person.log 
log4j.appender.person.MaxFileSize=250000KB 
log4j.appender.person.MaxBackupIndex=20
log4j.appender.person.Append = true
log4j.appender.person.layout=org.apache.log4j.PatternLayout 
log4j.appender.person.layout.ConversionPattern=%d{MM-dd-yyyy hh:mm:ss} %p [%c.%M()]:%m%x%n
log4j.additivity.gov.va.vba.corp.person=false
log4j.additivity.gov.va.vba.corp.webservice.person=false

log4j.appender.vetsnet = org.apache.log4j.RollingFileAppender
log4j.appender.vetsnet.File = @log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/vetsnet.log
log4j.appender.vetsnet.MaxFileSize = 250000KB
log4j.appender.vetsnet.MaxBackupIndex=20
log4j.appender.vetsnet.Append = true
log4j.appender.vetsnet.layout = org.apache.log4j.PatternLayout
log4j.appender.vetsnet.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss,SSS} %c{1} [%p] %m%n
log4j.additivity.gov.va.vba.benefits.covers=false
log4j.additivity.gov.va.vba.benefits.share=false
log4j.additivity.gov.va.birls=false
log4j.additivity.gov.va.vba.corporate=false
log4j.additivity.gov.va.vba.ebenefits=false
log4j.additivity.gov.va.vba.stat=false
log4j.additivity.gov.va.vba.vetsnet=false

log4j.appender.fts = org.apache.log4j.RollingFileAppender
log4j.appender.fts.File = @log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/fts.log
log4j.appender.fts.MaxFileSize = 250000KB
log4j.appender.fts.MaxBackupIndex=20
log4j.appender.fts.Append = true
log4j.appender.fts.layout = org.apache.log4j.PatternLayout
log4j.appender.fts.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss,SSS} %c{1} [%p] %m%n
log4j.additivity.gov.va.vba.vetsnet.fts=false

log4j.appender.rbps = org.apache.log4j.RollingFileAppender
log4j.appender.rbps.File = @log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/rbps.log
log4j.appender.rbps.MaxFileSize = 250000KB
log4j.appender.rbps.MaxBackupIndex=20
log4j.appender.rbps.Append = true
log4j.appender.rbps.layout = org.apache.log4j.PatternLayout
log4j.appender.rbps.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss,SSS} %c{1} [%p] %m%n
log4j.additivity.gov.va.vba.rbps=false

log4j.appender.vis = org.apache.log4j.RollingFileAppender
log4j.appender.vis.File = @log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/vis.log
log4j.appender.vis.MaxFileSize = 250000KB
log4j.appender.vis.MaxBackupIndex=20
log4j.appender.vis.Append = true
log4j.appender.vis.layout = org.apache.log4j.PatternLayout
log4j.appender.vis.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss,SSS} %c{1} [%p] %m%n
log4j.additivity.gov.va.vba.vis=false

### more from vetsnet 10/7/2011
###############################
###############################
	# log4j.properties configuration file for VBMS

	# The following properties configure the console (stdout) appender	 
	#log4j.appender.stdout=org.apache.log4j.ConsoleAppender 
	#log4j.appender.stdout.layout=org.apache.log4j.PatternLayout  
	#log4j.appender.stdout.layout.ConversionPattern=%X{SRV}:%X{MTH}:TID=%X{TID}:%5p [%d{HH:mm:ss}] (%F:%M:%L) - %m%n
		
	# The following properties configure the VBA log file
	log4j.appender.VBA=org.apache.log4j.DailyRollingFileAppender
	log4j.appender.VBA.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_VBMS_vetsnet.log
	log4j.appender.VBA.DatePattern='.'yyyy-MM-dd
	log4j.appender.VBA.MaxFileSize = 250000KB
	log4j.appender.VBA.MaxBackupIndex=20
	log4j.appender.VBA.Append=true
	log4j.appender.VBA.layout=org.apache.log4j.PatternLayout
	log4j.appender.VBA.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-30.30X{SRV}:%-20.20X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	#more from vetsnet 10/28/2011
    # The following properties configure the RBA log file
	log4j.appender.RBA=org.apache.log4j.DailyRollingFileAppender
	log4j.appender.RBA.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VBMSR_RBA_vetsnet.log
	log4j.appender.RBA.DatePattern='.'yyyy-MM-dd
	log4j.appender.RBA.MaxFileSize = 250000KB
	log4j.appender.RBA.MaxBackupIndex=20
	log4j.appender.RBA.Append=true
	log4j.appender.RBA.layout=org.apache.log4j.PatternLayout
	log4j.appender.RBA.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-30.30X{SRV}:%-20.20X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	#more from vetsnet 10/28/2011
    # The following properties configure the MAPD log file
	log4j.appender.MAPD=org.apache.log4j.DailyRollingFileAppender
	log4j.appender.MAPD.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VBMAP_MAPD_vetsnet.log
	log4j.appender.MAPD.DatePattern='.'yyyy-MM-dd
	log4j.appender.MAPD.MaxFileSize = 250000KB
	log4j.appender.MAPD.MaxBackupIndex=20
	log4j.appender.MAPD.Append=true
	log4j.appender.MAPD.layout=org.apache.log4j.PatternLayout
	log4j.appender.MAPD.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-30.30X{SRV}:%-20.20X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
    #more from vetsnet 10/28/2011
    # The following properties configure the SHARED LIBS for RBA/MAPD log file
	log4j.appender.VBMSHARED=org.apache.log4j.DailyRollingFileAppender
	log4j.appender.VBMSHARED.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VBMAP_VBMSR_SHAREDLIB.log
	log4j.appender.VBMSHARED.DatePattern='.'yyyy-MM-dd
	log4j.appender.VBMSHARED.MaxFileSize = 250000KB
	log4j.appender.VBMSHARED.MaxBackupIndex=20
	log4j.appender.VBMSHARED.Append=true
	log4j.appender.VBMSHARED.layout=org.apache.log4j.PatternLayout
	log4j.appender.VBMSHARED.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-30.30X{SRV}:%-20.20X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n  
	# end of vetsnet

log4j.appender.vonapp = org.apache.log4j.RollingFileAppender
log4j.appender.vonapp.File = @log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/vonapp.log
log4j.appender.vonapp.MaxFileSize = 250000KB
log4j.appender.vonapp.MaxBackupIndex=20
log4j.appender.vonapp.Append = true
log4j.appender.vonapp.layout = org.apache.log4j.PatternLayout
log4j.appender.vonapp.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss,SSS} %c{1} [%p] %m%n

	########################################
	#                                      #
	#      AWARDS SERVICES FILTERS         #
	#                                      #
	########################################

	# The following properties configure the DependencyDecisionEJB log file
	# log4j.appender.DEPENDEC=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.DEPENDEC.DatePattern='.'yyyy-MM-dd
	# log4j.appender.DEPENDEC.file=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_DependencyDecisionEJB.log
	# log4j.appender.DEPENDEC.layout=org.apache.log4j.PatternLayout
	# log4j.appender.DEPENDEC.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.DEPENDEC.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.DEPENDEC.filter.1.StringToMatch=DependencyDecisionEJB
	# log4j.appender.DEPENDEC.filter.1.AcceptOnMatch=true
	# log4j.appender.DEPENDEC.filter.1.MdcKey=SRV
	# log4j.appender.DEPENDEC.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.DEPENDEC.filter.2.LevelMin=DEBUG
	# log4j.appender.DEPENDEC.filter.2.AcceptOnMatch=true
	# log4j.appender.DEPENDEC.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.DEPENDEC.MaxFileSize = 250000KB
	# log4j.appender.DEPENDEC.MaxBackupIndex=20
	
		
	# The following properties configure the FinancialDecisionEJB log file 
	# log4j.appender.FINDEC=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.FINDEC.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_FinancialDecisionEJB.log
	# log4j.appender.FINDEC.DatePattern='.'yyyy-MM-dd
	# log4j.appender.FINDEC.Append=true
	# log4j.appender.FINDEC.layout=org.apache.log4j.PatternLayout
	# log4j.appender.FINDEC.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.FINDEC.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.FINDEC.filter.1.StringToMatch=FinancialDecisionEJB
	# log4j.appender.FINDEC.filter.1.MdcKey=SRV
	# log4j.appender.FINDEC.filter.1.AcceptOnMatch=false
	# log4j.appender.FINDEC.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.FINDEC.filter.2.LevelMin=DEBUG
	# log4j.appender.FINDEC.filter.2.AcceptOnMatch=true
	# log4j.appender.FINDEC.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.FINDEC.MaxFileSize = 250000KB
	# log4j.appender.FINDEC.MaxBackupIndex=20

		
	# The following properties configure the GcpAwardEJB log file
	# log4j.appender.GCPAWARD=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.GCPAWARD.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_GcpAwardEJB.log
	# log4j.appender.GCPAWARD.DatePattern='.'yyyy-MM-dd
	# log4j.appender.GCPAWARD.Append=true
	# log4j.appender.GCPAWARD.layout=org.apache.log4j.PatternLayout
	# log4j.appender.GCPAWARD.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.GCPAWARD.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.GCPAWARD.filter.1.StringToMatch=GcpAwardEJB
	# log4j.appender.GCPAWARD.filter.1.MdcKey=SRV
	# log4j.appender.GCPAWARD.filter.1.AcceptOnMatch=false
	# log4j.appender.GCPAWARD.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.GCPAWARD.filter.2.LevelMin=DEBUG
	# log4j.appender.GCPAWARD.filter.2.AcceptOnMatch=true
	# log4j.appender.GCPAWARD.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.GCPAWARD.MaxFileSize = 250000KB
	# log4j.appender.GCPAWARD.MaxBackupIndex=20


	# The following properties configure the ReadAwardEJB log file
	# log4j.appender.READAWARD=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.READAWARD.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_RcpAWARD.log
	# log4j.appender.READAWARD.DatePattern='.'yyyy-MM-dd
	# log4j.appender.READAWARD.Append=true
	# log4j.appender.READAWARD.layout=org.apache.log4j.PatternLayout
	# log4j.appender.READAWARD.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.READAWARD.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.READAWARD.filter.1.StringToMatch=RcpAwardEJB
	# log4j.appender.READAWARD.filter.1.MdcKey=SRV
	# log4j.appender.READAWARD.filter.1.AcceptOnMatch=false
	# log4j.appender.READAWARD.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.READAWARD.filter.2.LevelMin=DEBUG
	# log4j.appender.READAWARD.filter.2.AcceptOnMatch=true
	# log4j.appender.READAWARD.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.READAWARD.MaxFileSize = 250000KB
	# log4j.appender.READAWARD.MaxBackupIndex=20
	
	
	# The following properties configure the XcpCrPtcpntAwdEJB log file
	# log4j.appender.XCPCRPTCPNTAWD=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.XCPCRPTCPNTAWD.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_XcpCrPtcpntAwdEJB.log
	# log4j.appender.XCPCRPTCPNTAWD.DatePattern='.'yyyy-MM-dd
	# log4j.appender.XCPCRPTCPNTAWD.Append=true
	# log4j.appender.XCPCRPTCPNTAWD.layout=org.apache.log4j.PatternLayout
	# log4j.appender.XCPCRPTCPNTAWD.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.XCPCRPTCPNTAWD.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.XCPCRPTCPNTAWD.filter.1.StringToMatch=XcpCrPtcpntAwdEJB
	# log4j.appender.XCPCRPTCPNTAWD.filter.1.MdcKey=SRV
	# log4j.appender.XCPCRPTCPNTAWD.filter.1.AcceptOnMatch=false
	# log4j.appender.XCPCRPTCPNTAWD.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.XCPCRPTCPNTAWD.filter.2.LevelMin=DEBUG
	# log4j.appender.XCPCRPTCPNTAWD.filter.2.AcceptOnMatch=true
	# log4j.appender.XCPCRPTCPNTAWD.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.XCPCRPTCPNTAWD.MaxFileSize = 250000KB
	# log4j.appender.XCPCRPTCPNTAWD.MaxBackupIndex=20
	

	# The following properties configure the ZcpAwardAuthEJB log file
	# log4j.appender.ZCPAWDAUTH=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.ZCPAWDAUTH.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_ZcpAwardAuthEJB.log
	# log4j.appender.ZCPAWDAUTH.DatePattern='.'yyyy-MM-dd
	# log4j.appender.ZCPAWDAUTH.Append=true
	# log4j.appender.ZCPAWDAUTH.layout=org.apache.log4j.PatternLayout
	# log4j.appender.ZCPAWDAUTH.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.ZCPAWDAUTH.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.ZCPAWDAUTH.filter.1.StringToMatch=ZcpAwardAuthEJB
	# log4j.appender.ZCPAWDAUTH.filter.1.MdcKey=SRV
	# log4j.appender.ZCPAWDAUTH.filter.1.AcceptOnMatch=false
	# log4j.appender.ZCPAWDAUTH.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.ZCPAWDAUTH.filter.2.LevelMin=DEBUG
	# log4j.appender.ZCPAWDAUTH.filter.2.AcceptOnMatch=true
	# log4j.appender.ZCPAWDAUTH.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.ZCPAWDAUTH.MaxFileSize = 250000KB
	# log4j.appender.ZCPAWDAUTH.MaxBackupIndex=20
	
	############################################
	#                                          #
	#      COMMON SERVICES LOG FILTERS         #
	#                                          #
	############################################
	
	################
	#   PILOT 1    #
	################
	
	# The following properties configure the RvnMltyPersonEJB log file
	# log4j.appender.RVNMLTYPERSON=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.RVNMLTYPERSON.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_RvnMltyPersonEJB.log
	# log4j.appender.RVNMLTYPERSON.DatePattern='.'yyyy-MM-dd
	# log4j.appender.RVNMLTYPERSON.Append=true
	# log4j.appender.RVNMLTYPERSON.layout=org.apache.log4j.PatternLayout
	# log4j.appender.RVNMLTYPERSON.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.RVNMLTYPERSON.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.RVNMLTYPERSON.filter.1.StringToMatch=RvnMltyPersonEJB
	# log4j.appender.RVNMLTYPERSON.filter.1.MdcKey=SRV
	# log4j.appender.RVNMLTYPERSON.filter.1.AcceptOnMatch=false
	# log4j.appender.RVNMLTYPERSON.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.RVNMLTYPERSON.filter.2.LevelMin=DEBUG
	# log4j.appender.RVNMLTYPERSON.filter.2.AcceptOnMatch=true
	# log4j.appender.RVNMLTYPERSON.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.RVNMLTYPERSON.MaxFileSize = 250000KB
	# log4j.appender.RVNMLTYPERSON.MaxBackupIndex=20
	
	
	# The following properties configure the SvnMltyDecEJB log file
	# log4j.appender.SVNMLTYDEC=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.SVNMLTYDEC.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_SvnMltyDecEJB.log
	# log4j.appender.SVNMLTYDEC.DatePattern='.'yyyy-MM-dd
	# log4j.appender.SVNMLTYDEC.Append=true
	# log4j.appender.SVNMLTYDEC.layout=org.apache.log4j.PatternLayout
	# log4j.appender.SVNMLTYDEC.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.SVNMLTYDEC.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.SVNMLTYDEC.filter.1.StringToMatch=SvnMltyDecEJB
	# log4j.appender.SVNMLTYDEC.filter.1.MdcKey=SRV
	# log4j.appender.SVNMLTYDEC.filter.1.AcceptOnMatch=false
	# log4j.appender.SVNMLTYDEC.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.SVNMLTYDEC.filter.2.LevelMin=DEBUG
	# log4j.appender.SVNMLTYDEC.filter.2.AcceptOnMatch=true
	# log4j.appender.SVNMLTYDEC.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.SVNMLTYDEC.MaxFileSize = 250000KB
	# log4j.appender.SVNMLTYDEC.MaxBackupIndex=20
	
	
	# The following properties configure the SvnMltyTheatrEJB log file
	# log4j.appender.SVNMLTYTHEATR=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.SVNMLTYTHEATR.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_SvnMltyTheatrEJB.log
	# log4j.appender.SVNMLTYTHEATR.DatePattern='.'yyyy-MM-dd
	# log4j.appender.SVNMLTYTHEATR.Append=true
	# log4j.appender.SVNMLTYTHEATR.layout=org.apache.log4j.PatternLayout
	# log4j.appender.SVNMLTYTHEATR.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.SVNMLTYTHEATR.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.SVNMLTYTHEATR.filter.1.StringToMatch=SvnMltyTheatrEJB
	# log4j.appender.SVNMLTYTHEATR.filter.1.MdcKey=SRV
	# log4j.appender.SVNMLTYTHEATR.filter.1.AcceptOnMatch=false
	# log4j.appender.SVNMLTYTHEATR.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.SVNMLTYTHEATR.filter.2.LevelMin=DEBUG
	# log4j.appender.SVNMLTYTHEATR.filter.2.AcceptOnMatch=true
	# log4j.appender.SVNMLTYTHEATR.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.SVNMLTYTHEATR.MaxFileSize = 250000KB
	# log4j.appender.SVNMLTYTHEATR.MaxBackupIndex=20


	# The following properties configure the SvnPowEJB log file
	# log4j.appender.SVNPOW=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.SVNPOW.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_SvnPowEJB.log
	# log4j.appender.SVNPOW.DatePattern='.'yyyy-MM-dd
	# log4j.appender.SVNPOW.Append=true
	# log4j.appender.SVNPOW.layout=org.apache.log4j.PatternLayout
	# log4j.appender.SVNPOW.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.SVNPOW.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.SVNPOW.filter.1.StringToMatch=SvnPowEJB
	# log4j.appender.SVNPOW.filter.1.MdcKey=SRV
	# log4j.appender.SVNPOW.filter.1.AcceptOnMatch=false
	# log4j.appender.SVNPOW.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.SVNPOW.filter.2.LevelMin=DEBUG
	# log4j.appender.SVNPOW.filter.2.AcceptOnMatch=true
	# log4j.appender.SVNPOW.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.SVNPOW.MaxFileSize = 250000KB
	# log4j.appender.SVNPOW.MaxBackupIndex=20

	
	
	# The following properties configure the SvnSvcPeriodEJB log file
	# log4j.appender.SVNSVCPERIOD=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.SVNSVCPERIOD.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_SvnSvcPeriodEJB.log
	# log4j.appender.SVNSVCPERIOD.DatePattern='.'yyyy-MM-dd
	# log4j.appender.SVNSVCPERIOD.Append=true
	# log4j.appender.SVNSVCPERIOD.layout=org.apache.log4j.PatternLayout
	# log4j.appender.SVNSVCPERIOD.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.SVNSVCPERIOD.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.SVNSVCPERIOD.filter.1.StringToMatch=SvnSvcPeriodEJB
	# log4j.appender.SVNSVCPERIOD.filter.1.MdcKey=SRV
	# log4j.appender.SVNSVCPERIOD.filter.1.AcceptOnMatch=false
	# log4j.appender.SVNSVCPERIOD.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.SVNSVCPERIOD.filter.2.LevelMin=DEBUG
	# log4j.appender.SVNSVCPERIOD.filter.2.AcceptOnMatch=true
	# log4j.appender.SVNSVCPERIOD.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.SVNSVCPERIOD.MaxFileSize = 250000KB
	# log4j.appender.SVNSVCPERIOD.MaxBackupIndex=20


	# The following properties configure the XvnRdPrsnProfleEJB log file
	# log4j.appender.XVNRDPRSNPROFILE=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.XVNRDPRSNPROFILE.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_XvnRdPrsnProfleEJB.log
	# log4j.appender.XVNRDPRSNPROFILE.DatePattern='.'yyyy-MM-dd
	# log4j.appender.XVNRDPRSNPROFILE.Append=true
	# log4j.appender.XVNRDPRSNPROFILE.layout=org.apache.log4j.PatternLayout
	# log4j.appender.XVNRDPRSNPROFILE.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.XVNRDPRSNPROFILE.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.XVNRDPRSNPROFILE.filter.1.StringToMatch=XvnRdPrsnProfleEJB
	# log4j.appender.XVNRDPRSNPROFILE.filter.1.MdcKey=SRV
	# log4j.appender.XVNRDPRSNPROFILE.filter.1.AcceptOnMatch=false
	# log4j.appender.XVNRDPRSNPROFILE.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.XVNRDPRSNPROFILE.filter.2.LevelMin=DEBUG
	# log4j.appender.XVNRDPRSNPROFILE.filter.2.AcceptOnMatch=true
	# log4j.appender.XVNRDPRSNPROFILE.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.XVNRDPRSNPROFILE.MaxFileSize = 250000KB
	# log4j.appender.XVNRDPRSNPROFILE.MaxBackupIndex=20


	# The following properties configure the SvnTypeEJB log file
	# log4j.appender.SVNTYPE=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.SVNTYPE.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_SvnTypeEJB.log
	# log4j.appender.SVNTYPE.DatePattern='.'yyyy-MM-dd
	# log4j.appender.SVNTYPE.Append=true
	# log4j.appender.SVNTYPE.layout=org.apache.log4j.PatternLayout
	# log4j.appender.SVNTYPE.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.SVNTYPE.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.SVNTYPE.filter.1.StringToMatch=SvnTypeEJB
	# log4j.appender.SVNTYPE.filter.1.MdcKey=SRV
	# log4j.appender.SVNTYPE.filter.1.AcceptOnMatch=false
	# log4j.appender.SVNTYPE.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.SVNTYPE.filter.2.LevelMin=DEBUG
	# log4j.appender.SVNTYPE.filter.2.AcceptOnMatch=true
	# log4j.appender.SVNTYPE.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.SVNTYPE.MaxFileSize = 250000KB
	# log4j.appender.SVNTYPE.MaxBackupIndex=20
	

	# The following properties configure the QvnPersonEJB log file
	# log4j.appender.QVNPERSON=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.QVNPERSON.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_QvnPersonEJB.log
	# log4j.appender.QVNPERSON.DatePattern='.'yyyy-MM-dd
	# log4j.appender.QVNPERSON.Append=true
	# log4j.appender.QVNPERSON.layout=org.apache.log4j.PatternLayout
	# log4j.appender.QVNPERSON.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.QVNPERSON.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.QVNPERSON.filter.1.StringToMatch=QvnPersonEJB
	# log4j.appender.QVNPERSON.filter.1.MdcKey=SRV
	# log4j.appender.QVNPERSON.filter.1.AcceptOnMatch=false
	# log4j.appender.QVNPERSON.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.QVNPERSON.filter.2.LevelMin=DEBUG
	# log4j.appender.QVNPERSON.filter.2.AcceptOnMatch=true
	# log4j.appender.QVNPERSON.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.QVNPERSON.MaxFileSize = 250000KB
	# log4j.appender.QVNPERSON.MaxBackupIndex=20

	
	# The following properties configure the WvnSvcPeriodEJB log file
	# log4j.appender.WVNSVCPERIOD=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.WVNSVCPERIOD.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_WvnSvcPeriodEJB.log
	# log4j.appender.WVNSVCPERIOD.DatePattern='.'yyyy-MM-dd
	# log4j.appender.WVNSVCPERIOD.Append=true
	# log4j.appender.WVNSVCPERIOD.layout=org.apache.log4j.PatternLayout
	# log4j.appender.WVNSVCPERIOD.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.WVNSVCPERIOD.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.WVNSVCPERIOD.filter.1.StringToMatch=WvnSvcPeriodEJB
	# log4j.appender.WVNSVCPERIOD.filter.1.MdcKey=SRV
	# log4j.appender.WVNSVCPERIOD.filter.1.AcceptOnMatch=false
	# log4j.appender.WVNSVCPERIOD.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.WVNSVCPERIOD.filter.2.LevelMin=DEBUG
	# log4j.appender.WVNSVCPERIOD.filter.2.AcceptOnMatch=true
	# log4j.appender.WVNSVCPERIOD.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.WVNSVCPERIOD.MaxFileSize = 250000KB
	# log4j.appender.WVNSVCPERIOD.MaxBackupIndex=20
	
	
	# The following properties configure the WvnMltyTheatrEJB log file
	# log4j.appender.WVNMLTYTHEATR=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.WVNMLTYTHEATR.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_WvnMltyTheatrEJB.log
	# log4j.appender.WVNMLTYTHEATR.DatePattern='.'yyyy-MM-dd
	# log4j.appender.WVNMLTYTHEATR.Append=true
	# log4j.appender.WVNMLTYTHEATR.layout=org.apache.log4j.PatternLayout
	# log4j.appender.WVNMLTYTHEATR.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.WVNMLTYTHEATR.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.WVNMLTYTHEATR.filter.1.StringToMatch=WvnMltyTheatrEJB
	# log4j.appender.WVNMLTYTHEATR.filter.1.MdcKey=SRV
	# log4j.appender.WVNMLTYTHEATR.filter.1.AcceptOnMatch=false
	# log4j.appender.WVNMLTYTHEATR.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.WVNMLTYTHEATR.filter.2.LevelMin=DEBUG
	# log4j.appender.WVNMLTYTHEATR.filter.2.AcceptOnMatch=true
	# log4j.appender.WVNMLTYTHEATR.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.WVNMLTYTHEATR.MaxFileSize = 250000KB
	# log4j.appender.WVNMLTYTHEATR.MaxBackupIndex=20
	
	
	# The following properties configure the QvnMltyPersonEJB log file
	# log4j.appender.QVNMLTYPERSON=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.QVNMLTYPERSON.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_QvnMltyPersonEJB.log
	# log4j.appender.QVNMLTYPERSON.DatePattern='.'yyyy-MM-dd
	# log4j.appender.QVNMLTYPERSON.Append=true
	# log4j.appender.QVNMLTYPERSON.layout=org.apache.log4j.PatternLayout
	# log4j.appender.QVNMLTYPERSON.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.QVNMLTYPERSON.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.QVNMLTYPERSON.filter.1.StringToMatch=QvnMltyPersonEJB
	# log4j.appender.QVNMLTYPERSON.filter.1.MdcKey=SRV
	# log4j.appender.QVNMLTYPERSON.filter.1.AcceptOnMatch=false
	# log4j.appender.QVNMLTYPERSON.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.QVNMLTYPERSON.filter.2.LevelMin=DEBUG
	# log4j.appender.QVNMLTYPERSON.filter.2.AcceptOnMatch=true
	# log4j.appender.QVNMLTYPERSON.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.QVNMLTYPERSON.MaxFileSize = 250000KB
	# log4j.appender.QVNMLTYPERSON.MaxBackupIndex=20


	# The following properties configure the RvnPersonEJB log file
	# log4j.appender.RVNPERSON=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.RVNPERSON.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_RvnPersonEJB.log
	# log4j.appender.RVNPERSON.DatePattern='.'yyyy-MM-dd
	# log4j.appender.RVNPERSON.Append=true
	# log4j.appender.RVNPERSON.layout=org.apache.log4j.PatternLayout
	# log4j.appender.RVNPERSON.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.RVNPERSON.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.RVNPERSON.filter.1.StringToMatch=RvnPersonEJB
	# log4j.appender.RVNPERSON.filter.1.MdcKey=SRV
	# log4j.appender.RVNPERSON.filter.1.AcceptOnMatch=false
	# log4j.appender.RVNPERSON.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.RVNPERSON.filter.2.LevelMin=DEBUG
	# log4j.appender.RVNPERSON.filter.2.AcceptOnMatch=true
	# log4j.appender.RVNPERSON.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.RVNPERSON.MaxFileSize = 250000KB
	# log4j.appender.RVNPERSON.MaxBackupIndex=20


	# The following properties configure the WvnPowEJB log file
	# log4j.appender.WVNPOW=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.WVNPOW.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_WvnPowEJB.log
	# log4j.appender.WVNPOW.DatePattern='.'yyyy-MM-dd
	# log4j.appender.WVNPOW.Append=true
	# log4j.appender.WVNPOW.layout=org.apache.log4j.PatternLayout
	# log4j.appender.WVNPOW.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.WVNPOW.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.WVNPOW.filter.1.StringToMatch=WvnPowEJB
	# log4j.appender.WVNPOW.filter.1.MdcKey=SRV
	# log4j.appender.WVNPOW.filter.1.AcceptOnMatch=false
	# log4j.appender.WVNPOW.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.WVNPOW.filter.2.LevelMin=DEBUG
	# log4j.appender.WVNPOW.filter.2.AcceptOnMatch=true
	# log4j.appender.WVNPOW.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.WVNPOW.MaxFileSize = 250000KB
	# log4j.appender.WVNPOW.MaxBackupIndex=20

	
	################
	#   PILOT 2    #
	################

	# The following properties configure the AvnPoaEJB log file
	# log4j.appender.AVNPOA=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.AVNPOA.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_AvnPoaEJB.log
	# log4j.appender.AVNPOA.DatePattern='.'yyyy-MM-dd
	# log4j.appender.AVNPOA.Append=true
	# log4j.appender.AVNPOA.layout=org.apache.log4j.PatternLayout
	# log4j.appender.AVNPOA.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.AVNPOA.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.AVNPOA.filter.1.StringToMatch=AvnPoaEJB
	# log4j.appender.AVNPOA.filter.1.MdcKey=SRV
	# log4j.appender.AVNPOA.filter.1.AcceptOnMatch=false
	# log4j.appender.AVNPOA.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.AVNPOA.filter.2.LevelMin=DEBUG
	# log4j.appender.AVNPOA.filter.2.AcceptOnMatch=true
	# log4j.appender.AVNPOA.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.AVNPOA.MaxFileSize = 250000KB
	# log4j.appender.AVNPOA.MaxBackupIndex=20
	
	
	# The following properties configure the AvnCntryTypeEJB log file
	# log4j.appender.AVNCNTRYTYPE=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.AVNCNTRYTYPE.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_AvnCntryTypeEJB.log
	# log4j.appender.AVNCNTRYTYPE.DatePattern='.'yyyy-MM-dd
	# log4j.appender.AVNCNTRYTYPE.Append=true
	# log4j.appender.AVNCNTRYTYPE.layout=org.apache.log4j.PatternLayout
	# log4j.appender.AVNCNTRYTYPE.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.AVNCNTRYTYPE.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.AVNCNTRYTYPE.filter.1.StringToMatch=AvnCntryTypeEJB
	# log4j.appender.AVNCNTRYTYPE.filter.1.MdcKey=SRV
	# log4j.appender.AVNCNTRYTYPE.filter.1.AcceptOnMatch=false
	# log4j.appender.AVNCNTRYTYPE.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.AVNCNTRYTYPE.filter.2.LevelMin=DEBUG
	# log4j.appender.AVNCNTRYTYPE.filter.2.AcceptOnMatch=true
	# log4j.appender.AVNCNTRYTYPE.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.AVNCNTRYTYPE.MaxFileSize = 250000KB
	# log4j.appender.AVNCNTRYTYPE.MaxBackupIndex=20

	
	# The following properties configure the PvnPerSpclStaEJB log file
	# log4j.appender.PVNPERSPCLSTA=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.PVNPERSPCLSTA.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_PvnPerSpclStaEJB.log
	# log4j.appender.PVNPERSPCLSTA.DatePattern='.'yyyy-MM-dd
	# log4j.appender.PVNPERSPCLSTA.Append=true
	# log4j.appender.PVNPERSPCLSTA.layout=org.apache.log4j.PatternLayout
	# log4j.appender.PVNPERSPCLSTA.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.PVNPERSPCLSTA.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.PVNPERSPCLSTA.filter.1.StringToMatch=PvnPerSpclStaEJB
	# log4j.appender.PVNPERSPCLSTA.filter.1.MdcKey=SRV
	# log4j.appender.PVNPERSPCLSTA.filter.1.AcceptOnMatch=false
	# log4j.appender.PVNPERSPCLSTA.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.PVNPERSPCLSTA.filter.2.LevelMin=DEBUG
	# log4j.appender.PVNPERSPCLSTA.filter.2.AcceptOnMatch=true
	# log4j.appender.PVNPERSPCLSTA.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.PVNPERSPCLSTA.MaxFileSize = 250000KB
	# log4j.appender.PVNPERSPCLSTA.MaxBackupIndex=20

	
	
	# The following properties configure the CvnPersonEJB log file
	# log4j.appender.CVNPERSON=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.CVNPERSON.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_CvnPersonEJB.log
	# log4j.appender.CVNPERSON.DatePattern='.'yyyy-MM-dd
	# log4j.appender.CVNPERSON.Append=true
	# log4j.appender.CVNPERSON.layout=org.apache.log4j.PatternLayout
	# log4j.appender.CVNPERSON.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.CVNPERSON.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.CVNPERSON.filter.1.StringToMatch=CvnPersonEJB
	# log4j.appender.CVNPERSON.filter.1.MdcKey=SRV
	# log4j.appender.CVNPERSON.filter.1.AcceptOnMatch=false
	# log4j.appender.CVNPERSON.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.CVNPERSON.filter.2.LevelMin=DEBUG
	# log4j.appender.CVNPERSON.filter.2.AcceptOnMatch=true
	# log4j.appender.CVNPERSON.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.CVNPERSON.MaxFileSize = 250000KB
	# log4j.appender.CVNPERSON.MaxBackupIndex=20
	

	# The following properties configure the CvnPtcpntAliasEJB log file
	# log4j.appender.CVNPTCPNTALIAS=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.CVNPTCPNTALIAS.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_CvnPtcpntAliasEJB.log
	# log4j.appender.CVNPTCPNTALIAS.DatePattern='.'yyyy-MM-dd
	# log4j.appender.CVNPTCPNTALIAS.Append=true
	# log4j.appender.CVNPTCPNTALIAS.layout=org.apache.log4j.PatternLayout
	# log4j.appender.CVNPTCPNTALIAS.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.CVNPTCPNTALIAS.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.CVNPTCPNTALIAS.filter.1.StringToMatch=CvnPtcpntAliasEJB
	# log4j.appender.CVNPTCPNTALIAS.filter.1.MdcKey=SRV
	# log4j.appender.CVNPTCPNTALIAS.filter.1.AcceptOnMatch=false
	# log4j.appender.CVNPTCPNTALIAS.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.CVNPTCPNTALIAS.filter.2.LevelMin=DEBUG
	# log4j.appender.CVNPTCPNTALIAS.filter.2.AcceptOnMatch=true
	# log4j.appender.CVNPTCPNTALIAS.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.CVNPTCPNTALIAS.MaxFileSize = 250000KB
	# log4j.appender.CVNPTCPNTALIAS.MaxBackupIndex=20
	
	
	# The following properties configure the XvnCrudPoaEJB log file
	# log4j.appender.XVNCRUDPOA=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.XVNCRUDPOA.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_XvnCrudPoaEJB.log
	# log4j.appender.XVNCRUDPOA.DatePattern='.'yyyy-MM-dd
	# log4j.appender.XVNCRUDPOA.Append=true
	# log4j.appender.XVNCRUDPOA.layout=org.apache.log4j.PatternLayout
	# log4j.appender.XVNCRUDPOA.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.XVNCRUDPOA.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.XVNCRUDPOA.filter.1.StringToMatch=XvnCrudPoaEJB
	# log4j.appender.XVNCRUDPOA.filter.1.MdcKey=SRV
	# log4j.appender.XVNCRUDPOA.filter.1.AcceptOnMatch=false
	# log4j.appender.XVNCRUDPOA.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.XVNCRUDPOA.filter.2.LevelMin=DEBUG
	# log4j.appender.XVNCRUDPOA.filter.2.AcceptOnMatch=true
	# log4j.appender.XVNCRUDPOA.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.XVNCRUDPOA.MaxFileSize = 250000KB
	# log4j.appender.XVNCRUDPOA.MaxBackupIndex=20
	
	
	# The following properties configure the WvnMltyDecEJB log file
	# log4j.appender.WVNMLTYDEC=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.WVNMLTYDEC.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_WvnMltyDecEJB.log
	# log4j.appender.WVNMLTYDEC.DatePattern='.'yyyy-MM-dd
	# log4j.appender.WVNMLTYDEC.Append=true
	# log4j.appender.WVNMLTYDEC.layout=org.apache.log4j.PatternLayout
	# log4j.appender.WVNMLTYDEC.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.WVNMLTYDEC.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.WVNMLTYDEC.filter.1.StringToMatch=WvnMltyDecEJB
	# log4j.appender.WVNMLTYDEC.filter.1.MdcKey=SRV
	# log4j.appender.WVNMLTYDEC.filter.1.AcceptOnMatch=false
	# log4j.appender.WVNMLTYDEC.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.WVNMLTYDEC.filter.2.LevelMin=DEBUG
	# log4j.appender.WVNMLTYDEC.filter.2.AcceptOnMatch=true
	# log4j.appender.WVNMLTYDEC.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.WVNMLTYDEC.MaxFileSize = 250000KB
	# log4j.appender.WVNMLTYDEC.MaxBackupIndex=20


	# The following properties configure the XvnRdCurPayAddrEJB log file
	# log4j.appender.XVNRDCURPAYADDR=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.XVNRDCURPAYADDR.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_XvnRdCurPayAddrEJB.log
	# log4j.appender.XVNRDCURPAYADDR.DatePattern='.'yyyy-MM-dd
	# log4j.appender.XVNRDCURPAYADDR.Append=true
	# log4j.appender.XVNRDCURPAYADDR.layout=org.apache.log4j.PatternLayout
	# log4j.appender.XVNRDCURPAYADDR.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.XVNRDCURPAYADDR.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.XVNRDCURPAYADDR.filter.1.StringToMatch=XvnRdCurPayAddrEJB
	# log4j.appender.XVNRDCURPAYADDR.filter.1.MdcKey=SRV
	# log4j.appender.XVNRDCURPAYADDR.filter.1.AcceptOnMatch=false
	# log4j.appender.XVNRDCURPAYADDR.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.XVNRDCURPAYADDR.filter.2.LevelMin=DEBUG
	# log4j.appender.XVNRDCURPAYADDR.filter.2.AcceptOnMatch=true
	# log4j.appender.XVNRDCURPAYADDR.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.XVNRDCURPAYADDR.MaxFileSize = 250000KB
	# log4j.appender.XVNRDCURPAYADDR.MaxBackupIndex=20
	

	# The following properties configure the XvnUpMltyPayEJB log file
	# log4j.appender.XVNUPMLTYPAY=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.XVNUPMLTYPAY.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_XvnUpMltyPayEJB.log
	# log4j.appender.XVNUPMLTYPAY.DatePattern='.'yyyy-MM-dd
	# log4j.appender.XVNUPMLTYPAY.Append=true
	# log4j.appender.XVNUPMLTYPAY.layout=org.apache.log4j.PatternLayout
	# log4j.appender.XVNUPMLTYPAY.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.XVNUPMLTYPAY.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.XVNUPMLTYPAY.filter.1.StringToMatch=XvnUpMltyPayEJB
	# log4j.appender.XVNUPMLTYPAY.filter.1.MdcKey=SRV
	# log4j.appender.XVNUPMLTYPAY.filter.1.AcceptOnMatch=false
	# log4j.appender.XVNUPMLTYPAY.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.XVNUPMLTYPAY.filter.2.LevelMin=DEBUG
	# log4j.appender.XVNUPMLTYPAY.filter.2.AcceptOnMatch=true
	# log4j.appender.XVNUPMLTYPAY.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.XVNUPMLTYPAY.MaxFileSize = 250000KB
	# log4j.appender.XVNUPMLTYPAY.MaxBackupIndex=20

	
	# The following properties configure the YvnUpdPersonEJB log file
	# log4j.appender.YVNUPDPERSON=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.YVNUPDPERSON.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_YvnUpdPersonEJB.log
	# log4j.appender.YVNUPDPERSON.DatePattern='.'yyyy-MM-dd
	# log4j.appender.YVNUPDPERSON.Append=true
	# log4j.appender.YVNUPDPERSON.layout=org.apache.log4j.PatternLayout
	# log4j.appender.YVNUPDPERSON.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.YVNUPDPERSON.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.YVNUPDPERSON.filter.1.StringToMatch=YvnUpdPersonEJB
	# log4j.appender.YVNUPDPERSON.filter.1.MdcKey=SRV
	# log4j.appender.YVNUPDPERSON.filter.1.AcceptOnMatch=false
	# log4j.appender.YVNUPDPERSON.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.YVNUPDPERSON.filter.2.LevelMin=DEBUG
	# log4j.appender.YVNUPDPERSON.filter.2.AcceptOnMatch=true
	# log4j.appender.YVNUPDPERSON.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.YVNUPDPERSON.MaxFileSize = 250000KB
	# log4j.appender.YVNUPDPERSON.MaxBackupIndex=20
	
	
	# The following properties configure the XvnRdMltyPayEJB log file
	# log4j.appender.XVNRDMLTYPAY=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.XVNRDMLTYPAY.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_XvnRdMltyPayEJB.log
	# log4j.appender.XVNRDMLTYPAY.DatePattern='.'yyyy-MM-dd
	# log4j.appender.XVNRDMLTYPAY.Append=true
	# log4j.appender.XVNRDMLTYPAY.layout=org.apache.log4j.PatternLayout
	# log4j.appender.XVNRDMLTYPAY.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.XVNRDMLTYPAY.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.XVNRDMLTYPAY.filter.1.StringToMatch=XvnRdMltyPayEJB
	# log4j.appender.XVNRDMLTYPAY.filter.1.MdcKey=SRV
	# log4j.appender.XVNRDMLTYPAY.filter.1.AcceptOnMatch=false
	# log4j.appender.XVNRDMLTYPAY.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.XVNRDMLTYPAY.filter.2.LevelMin=DEBUG
	# log4j.appender.XVNRDMLTYPAY.filter.2.AcceptOnMatch=true
	# log4j.appender.XVNRDMLTYPAY.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.XVNRDMLTYPAY.MaxFileSize = 250000KB
	# log4j.appender.XVNRDMLTYPAY.MaxBackupIndex=20
	
	
	# The following properties configure the SvnPersonEJB log file
	# log4j.appender.SVNPERSON=org.apache.log4j.DailyRollingFileAppender
	# log4j.appender.SVNPERSON.File=@log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/VNSPT_SvnPersonEJB.log
	# log4j.appender.SVNPERSON.DatePattern='.'yyyy-MM-dd
	# log4j.appender.SVNPERSON.Append=true
	# log4j.appender.SVNPERSON.layout=org.apache.log4j.PatternLayout
	# log4j.appender.SVNPERSON.layout.ConversionPattern=%d{yyyyMMdd:HHmmssSSS}:%-20.20X{SRV}:%-15.15X{MTH}:%X{CLINAME}:TID=%-4.4X{TID}:%X{CLIMACH}:%X{STN}:%X{LOCID}:%-5p:[%c]:%m%n
	# log4j.appender.SVNPERSON.filter.1=gov.va.vba.benefits.arch.util.logging.MDCFilter
	# log4j.appender.SVNPERSON.filter.1.StringToMatch=SvnPersonEJB
	# log4j.appender.SVNPERSON.filter.1.MdcKey=SRV
	# log4j.appender.SVNPERSON.filter.1.AcceptOnMatch=false
	# log4j.appender.SVNPERSON.filter.2=org.apache.log4j.varia.LevelRangeFilter
	# log4j.appender.SVNPERSON.filter.2.LevelMin=DEBUG
	# log4j.appender.SVNPERSON.filter.2.AcceptOnMatch=true
	# log4j.appender.SVNPERSON.filter.3=org.apache.log4j.varia.DenyAllFilter
	# log4j.appender.SVNPERSON.MaxFileSize = 250000KB
	# log4j.appender.SVNPERSON.MaxBackupIndex=20

###############################
###############################

### AUDIT appenders configuration

log4j.appender.rootaudit = org.apache.log4j.DailyRollingFileAppender
log4j.appender.rootaudit.File = @log_file_location_prefix@/domains/VBAIntDomain/VBAIntCluster/root.audit
log4j.appender.rootaudit.DatePattern='.'yyyy-MM-dd
log4j.appender.rootaudit.Append = true
log4j.appender.rootaudit.layout = org.apache.log4j.PatternLayout
log4j.appender.rootaudit.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss,SSS}|%m%n
log4j.additivity.audit=false

