<?php
require_once('access.php');
class Sendpushs extends Access
{
	function __construct()
	{
		parent::__construct('sendpushs');
	}
	
	
	//retrieve
	function index()
	{
		$this->session->unset_userdata('searchterm');
		
		$pag = $this->config->item('pagination');
		$pag['base_url'] = site_url('sendpushs/index');
		$pag['total_rows'] = $this->sendpush->count_all();
		
		$data['feeds'] = $this->sendpush->get_all($pag['per_page'],$this->uri->segment(3));
		$data['pag'] = $pag;
		
		$content['content'] = $this->load->view('sendpushs/view',$data,true);		
		
		$this->load->view('template',$content);
	}
	
	function search()
	{
		$search_term = $this->searchterm_handler(array(
			"searchterm"=>$this->input->post('searchterm')
		));
		$data = $search_term;
		
		$pag = $this->config->item('pagination');
		
		$pag['base_url'] = site_url('sendpushs/search');
		$pag['total_rows'] = $this->sendpush->count_all_by($search_term);
		
		$data['feeds'] = $this->sendpush->get_all_by($search_term,$pag['per_page'],$this->uri->segment(3));
		$data['pag'] = $pag;
		
		$content['content'] = $this->load->view('sendpushs/search',$data,true);		
		$this->load->view('template',$content);
	}
	
	function searchterm_handler($searchterms = array())
	{
		$data = array();
		
		if ($this->input->server('REQUEST_METHOD')=='POST') {
			foreach ($searchterms as $name=>$term) {
				if ($term && trim($term) != " ") {
					$this->session->set_userdata($name,$term);
					$data[$name] = $term;
				} else {
					$this->session->unset_userdata($term);
					$data[$name] = "";
				}
			}
		} else {
			foreach ($searchterms as $name=>$term) {
				if ($this->session->userdata($name)) {
					$data[$name] = $this->session->userdata($name);
				} else { 
					$data[$name] = "";
				}
			}
		}
		return $data;
	}
	
	//create
	function add()
	{
		$this->check_access('add');
		
		if ($this->input->server('REQUEST_METHOD')=='POST') {			
			
			log_message('error','Start POST');
			
			$feed_data = $this->input->post();
			
			$this->sendpush->save($feed_data);	
			log_message('debug','сохранено');
			
			 $this->load->library('parse-php-sdk/src/Parse/ParseClient');
             $this->parseclient->initialize( 'j5IKI8Pd10ktvfHaHH1eD4S9PhLQsacL55fdXs3C', 'A7zySEFhTuZVYPzVghyvrmiu7CvTUc919aJdjMc0',  'ljXyD81MgCO0AytBzb3DYnRElxdtC0za0iJrCoTd' );

               $this->load->library('parse-php-sdk/src/Parse/ParsePush');
               
               
               $data = array("alert" => $feed_data["title"], "sound" => "01.caf","badge" => "Increment");

               // Push to Channels
               $this->parsepush->send(array(
                "channels" => ["global"],
                
                "title" => "NCNews!",
                "data" => $data
                ));

			
			
			
					
			$this->session->set_flashdata('success','news is successfully added.');
			redirect(site_url('sendpushs'));
			
			} else {
			
			//	$data['error'] = $upload_data['error'];
			}
		
		
		$content['content'] = $this->load->view('sendpushs/add',array(),true);
		$this->load->view('template',$content);
	}	
	
	
	
	//update
	function edit($feed_id=0)
	{
		$this->check_access('edit');
		
		if ($this->input->server('REQUEST_METHOD')=='POST') {
			if ($this->sendpush->save($this->input->post(),$feed_id)) {
				$this->session->set_flashdata('success','news is successfully updated.');
			} else {
				$this->session->set_flashdata('error','Database error occured.Please contact your system administrator.');
			}
			redirect(site_url('sendpushs'));
		}
		
		$data['news'] = $this->sendpush->get_info($feed_id);
		
		$content['content'] = $this->load->view('sendpushs/add',$data,true);		
		$this->load->view('template',$content);
	}
	
	

	//delete
	function delete($feed_id=0)
	{
		$this->check_access('delete');
		
		if ($this->sendpush->delete($feed_id)) {
			$this->session->set_flashdata('success','The push is successfully deleted.');
		} else {
			$this->session->set_flashdata('error','Database error occured.Please contact your system administrator.');
		}
		redirect(site_url('sendpushs'));
	}
	
	
	
	
	
	
	
	
	
	
}