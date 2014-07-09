function getData(index,url){
	for(var i=0;i<7;i++){
		if(i==index){
			$("#tab_"+i).attr("class","active");
		}else{
			$("#tab_"+i).attr("class","");
		}
	}
	if(0==index){
		$("#list_frame").attr("src",url);
	}else if(1==index){
		$("#list_frame").attr("src",url);
	}else if(2==index){
		$("#list_frame").attr("src",url);
	}else if(3==index){
		$("#list_frame").attr("src",url);
	}else if(4==index){
		$("#list_frame").attr("src",url);
	}else if(5==index){
		$("#list_frame").attr("src",url);
	}else if(6==index){
		$("#list_frame").attr("src",url);
	}
}