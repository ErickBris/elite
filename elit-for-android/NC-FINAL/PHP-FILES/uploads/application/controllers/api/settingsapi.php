<?php 
require_once(APPPATH.'/libraries/REST_Controller.php');

class Settingsapi extends REST_Controller
{
	function __construct()
	{
		parent::__construct();	
	}
	
	function index_get()
	{
		$feeds = $this->setting->get_all()->result();
		
		
		$data = array();
		$dataz = array();
		foreach ($feeds as $news) {
			
			$this->get_feed_images($news);
			$data[] = $news;
		}
		$dataz =array('setting'=>$data);
		
		$this->response($dataz);
	}
	
	function get_feed_images(&$news)
	{
		//$news->added = $this->ago($news->added);
		$news->images = $this->image->get_all_by_type($news->id, 'setting')->result();
	}
	/*
	function ago($time)
	{
		$time = mysql_to_unix($time);
		//$now = mysql_to_unix($this->category->get_now());
		
	   $periods = array("second", "minute", "hour", "day", "week", "month", "year", "decade");
	   $lengths = array("60","60","24","7","4.35","12","10");
	
	  // $difference = $now - $time;
	  	$tense = "ago";
	
	   for ($j = 0; $difference >= $lengths[$j] && $j < count($lengths)-1; $j++) {
	       $difference /= $lengths[$j];
	   }
	
	   $difference = round($difference);
	
	   if ($difference != 1) {
	       $periods[$j].= "s";
	   }
	
	   if ($difference==0) {
	   		return "Just Now";
	   } else {
	   		return "$difference $periods[$j] ago";
	   }
	}*/
}
?>