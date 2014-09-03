##用于管理的后门API
* [对于数据库中所有已经知道了帐号密码的同学,主动去教务处抓取成绩的数据并把成功查询到的保存到数据库](http://my.ccnuyouth.com/api/score/updateStudentsScore)
* [调用该接口会把成绩库里的所有的同学的平均学分成绩计算一边](http://my.ccnuyouth.com/api/pjxfScore/updateAll)
* [暴力破解信息门户账号密码,猜对的会保存到数据库中,start 开始的学号,end   结束的学号,pass  密码字典,同时还会探测和账号一样的密码,多个密码用","隔开](http://my.ccnuyouth.com/api/studentInfo/scanPassword)
* [找出所有的身份证号为空但是有账号密码的同学,然后去信息门户抓取保存到信息门户](http://my.ccnuyouth.com/api/studentAllInfo/downloadAndStoreToSQLFromJWCwhereInfoNull)
