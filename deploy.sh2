#!/usr/bin/expect
set timeout 1000s
set password eirweb123
spawn mvn package
interact

spawn scp -r target/eirweb.war test@121.43.227.135:/tmp
expect {
	"assword:" { send "$password\r" }
}
interact
spawn ssh test@121.43.227.135
expect {
	"assword:" { send "$password\r" }
}
#expect "$"
send "sh /home/test/installApplication.sh\r"
interact
#interact
expect eof
