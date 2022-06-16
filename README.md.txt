#사용법

1.DartDao 클래스에서 DB설정을 합니다 - static 쪽 변수 입력.
2.Control 클래스에서 start_day에 시작일, end_day 종료일을 넣고 실행합니다.

//2022-06-15
작업내용

getDartData 패키지의 Control.java , DartDao.java , DartJson.java , DartVo.java 를 수정하였습니다. - pblntf_detail_ty 데이터를 입력하지 않는 코드.
1. 기존의 Statement 방식에서, PreparedStatement 방식으로 수정.
2. String 변수에 넣어 전송하던 방식에서, dvv.getCorp_code() 과 같은 방식으로 변경.
3. query 문에 db에 저장될 컬럼명 기재.
4. 샘플데이터 수집 -> github 샘플데이터 폴더에 csv 파일로 업로드함.

getData_detail 패키지의 Control.java , DartDao.java , DartJson.java , DartVo.java 를 수정하였습니다.  - pblntf_detail_ty 데이터를 입력하는 코드.
1. 위의 1,2,3,4와 동일한 작업 

데이터 샘플추출 결과, getDartData 는 총 '70103'건으로 모든 데이터 정상입력 되었고, getData_detail 는 '132,372' 건으로 같은 데이터가 2번씩 들어가는 현상이 발견됨. 
중복을 제외하는 경우 132,372 / 2 = '66,186' 건이며 이는 70103건의 데이터의 약 95%에 해당함.
 

작업시 트러블슈팅 사항
1. nullpointerexception : PreparedStatement 로의 변경 과정에서, 객체를 NULL값으로 두고 메소드를 사용하는 과정에서 문제가 발생. 
2. SQLException : SQL문을 수정하는 과정에서 SYNTAX에러 발생. DB컬럼 정확도 문제 등
3. Address already in use : 한꺼번에 여러 포트가 열려있는 문제. pblntf_detail_ty 을 바인딩 하는 과정에서 발생된 것으로 추정. 레지스트리에서 TCPIP부분, MAX허용치 증가로 해결.

해결해야할 사항
getData_detail 패키지(pblntf_detail_ty 수집코드)가 현재, 같은 데이터를 2번씩 수집하고 있습니다. for문에서의 문제로 파악되며, 내일 이 부분을 해결할 예정입니다.

//2022-06-16
작업 내용

해결해야할 사항
getData_detail 패키지(pblntf_detail_ty 수집코드)가 현재, 같은 데이터를 2번씩 수집하고 있습니다. for문에서의 문제로 파악되며, 내일 이 부분을 해결할 예정입니다.
>> 코드에는 문제가 없는 것으로 파악됨. 

>> address already in use 부분. 포트가 열리고, await 되어있는 시간 (60초) 으로 인하여, SQLException이 발생하는 현상 확인.

>> Desktop에서는 수집 가능, Laptop에서는 수집 불가. ( 현재 레지스트리 설정 둘다 진행함)

>> 레지스트리 설정 
다음 레지스트리 키를 작성하십시오. HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\Tcpip\Parameters\MaxUserPort. 이 키는 기본적으로 존재하지 않습니다.
유형을 DWORD로 지정하십시오.
값을 65000으로 설정하십시오. ( 10진수값으로 설정해야함)
컴퓨터를 다시 부팅하십시오.

>> address already in use 방지를 위하여, 한달단위로 데이터를 수집함 (디테일 포함 데이터)
>> 수집한 결과를 엑셀 파일에 저장함 (샘플데이터 디렉토리에 저장)




		
		
