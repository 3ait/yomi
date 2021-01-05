
DIR=/opt/workspace/yomi #日志文件路径
DATABASE=yomi
USER=yomi
PASSWORD=-@2pXv58dS
HOSTNAME=localhost

#back up script

SUCCESSLOG=$DIR/log/success.log
FAILLOG=$DIR/log/error.log
DATE=$(date '+%Y%m%d')
LOGFILE=$DIR/backup_${DATE}.log
MAIL_TITLE=$DIR + "_backup"
FLAG=1

IP_ADD=$(hostname -I | awk '{print $1}')
OPTIONS="-h$HOSTNAME -u$USER -p$PASSWORD $DATABASE"

#判断备份文件存储目录是否存在，否则创建该目录IL_TITLE
if [ ! -d $DIR/log ] ;
then
	mkdir -p "$DIR/log"
fi

#切换至备份目录
cd $DIR
DUMPFILE=$DATABASE.sql #备份文件名
#使用mysqldump 命令备份制定数据库，并以格式化的时间戳命名备份文件
mysqldump $OPTIONS > $DUMPFILE
#判断数据库备份是否成功
if [[ $? == 0 ]]; then
		#输入备份成功的消息到日记文件
		echo "[$DUMPFILE] Backup successful! IP is $IP_ADD" >> $SUCCESSLOG
 else
		FLAG=0
		echo "[$DUMPFILE] Backup fail! IP is $IP_ADD" >> $FAILLOG
fi
git add $DUMPFILE
echo "add ${DUMPFILE} to git"
git commit -m "Auto buckup commit ${DATABASE} ${DATE} the file is ${DUMPFILE}"
git push origin master


if [[ $FLAG == 1 ]]; then
        echo ${DATE}_"no back up fail" >> $FAILLOG
fi


#cat $FAILLOG $SUCCESSLOG >> $LOGFILE
#mail -s "${MAIL_TITLE}" leo@matricle.com < ${LOGFILE}

