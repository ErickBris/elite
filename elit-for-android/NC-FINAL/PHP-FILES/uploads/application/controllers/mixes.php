<?php
require_once('access.php');
class Mixes extends Access
{
	function __construct()
	{
		parent::__construct('mixes');
		$this->load->library('uploadermix');
		//$this->output->enable_profiler(TRUE);
	}
	
	//create
	function add()
	{
		$this->check_access('add');
		
		if ($this->input->server('REQUEST_METHOD')=='POST') {			
			$upload_data = $this->uploadermix->upload($_FILES);
			
			if (!isset($upload_data['error'])) {
				$feed_data = $this->input->post();
				//$feed_mix['fileurl'] = $upload['file_name'];
				//$img_desc = $feed_data['image_desc'];
				unset($feed_data['image_desc']);
				unset($feed_data['images']);
				
				if ($feed_data) {
					foreach ($upload_data as $upload) {
						$image = array(
						//	'item_id'=>$feed_data['id'],
						//	'type' => 'mixes',
						//	'path' => $upload['file_name'],
						//	'width'=>$upload['image_width'],
						//	'height'=>$upload['image_height']
						);
						//$feed_mix['fileurl'] = $upload['file_name'];
						$feed_data['fileurl'] =  $upload['file_name'];
						//$this->image->save($image);
						$this->mix->save($feed_data);
						
					}
								
					$this->session->set_flashdata('success','mix is successfully added.');
				} else {
					$this->session->set_flashdata('error','Database error occured.Please contact your system administrator.');
				}
				redirect(site_url('mixes'));
			} else {
				$data['error'] = $upload_data['error'];
			}
		}
		
		$content['content'] = $this->load->view('mixes/add',array(),true);
		$this->load->view('template',$content);
	}
	
	//retrieve
	function index()
	{
		$this->session->unset_userdata('searchterm');
		
		$pag = $this->config->item('pagination');
		$pag['base_url'] = site_url('mixes/index');
		$pag['total_rows'] = $this->mix->count_all();
		
		$data['mixfeed'] = $this->mix->get_all($pag['per_page'],$this->uri->segment(3));
		$data['pag'] = $pag;
		
		$content['content'] = $this->load->view('mixes/view',$data,true);		
		
		$this->load->view('template',$content);
	}
	
	function search()
	{
		$search_term = $this->searchterm_handler(array(
			"searchterm"=>$this->input->post('searchterm')
		));
		$data = $search_term;
		
		$pag = $this->config->item('pagination');
		
		$pag['base_url'] = site_url('mixes/search');
		$pag['total_rows'] = $this->mix->count_all_by($search_term);
		
		$data['mixfeed'] = $this->mix->get_all_by($search_term,$pag['per_page'],$this->uri->segment(3));
		$data['pag'] = $pag;
		
		$content['content'] = $this->load->view('mixes/search',$data,true);		
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
	
	//update
	function edit($feed_id=0)
	{
		$this->check_access('edit');
		
		if ($this->input->server('REQUEST_METHOD')=='POST') {
			if ($this->mix->save($this->input->post(),$feed_id)) {
				$this->session->set_flashdata('success','mix is successfully updated.');
			} else {
				$this->session->set_flashdata('error','Database error occured.Please contact your system administrator.');
			}
			redirect(site_url('mixes'));
		}
		
		$data['editmix'] = $this->mix->get_info($feed_id);
		
		$content['content'] = $this->load->view('mixes/edit',$data,true);		
		$this->load->view('template',$content);
	}
	
	function upload($feed_id=0)
	{
		$this->check_access('edit');
		
		$upload_data = $this->uploadermix->upload($_FILES);
		
		if (!isset($upload_data['error'])) {
			foreach ($upload_data as $upload) {
				$image = array(
								'item_id'=> $feed_id,
								'type' => 'news',
								'path' => $upload['file_name'],
								'width'=>$upload['image_width'],
								'height'=>$upload['image_height']
							);
				//$this->image->save($image);
			}
		} else {
			$data['error'] = $upload_data['error'];
		}
		
		$data['editmix'] = $this->mix->get_info($feed_id);
		
		$content['content'] = $this->load->view('mixes/edit',$data,true);		
		$this->load->view('template',$content);
	}
	
	///////
	
	public function uploadz(){

        $config['upload_path'] = "/application/uploads/";
        $config['allowed_types'] = "jpg|jpeg|png|gif|flv|mp4|wmv|doc|docx|xsl|xslx|ppt|pptx|zip|rar|tar";
        $config['max_size']	= 2048;
        $config['max_width'] = 800;
        $config['max_height'] = 600;
        $config['encrypt_name'] = TRUE;

        $this->load->library('upload', $config);

        if ($this->upload->do_upload() == false) {
            $error = array('error' => $this->upload->display_errors());
            echo json_encode($error);
        }else{
            $data = $this->upload->data();
            echo json_encode($data);
        }
    }
	
	///////
	
	
	
	function publish($id = 0)
	{
		$this->check_access('publish');
		
		$feed_data = array(
			'status'=> 1
		);
			
		if ($this->mix->save($feed_data,$id)) {
			echo 'true';
		} else {
			echo 'false';
		}
	}
	
	function unpublish($id = 0)
	{
		$this->check_access('publish');
		
		$feed_data = array(
			'status'=> 0
		);
			
		if ($this->mix->save($feed_data,$id)) {
			echo 'true';
		} else {
			echo 'false';
		}
	}

	//delete
	function delete($feed_id=0)
	{
		$this->check_access('delete');
		
		$url = $this->mix->get_file($feed_id);
		
		//print_r ($url);
		unlink('./uploadsmix/'.$url);
		
		if ($this->mix->delete($feed_id)) {
			$this->session->set_flashdata('success','The mix is successfully deleted.');
		} else {
			$this->session->set_flashdata('error','Database error occured.Please contact your system administrator.');
		}
		
		
		redirect(site_url('mixes'));
	}
	
	/*
	//delete image
	function delete_image($feed_id,$image_id,$image_name)
	{
		$this->check_access('edit');
		
		if ($this->image->delete($image_id)) {
			unlink('./uploadsmix/'.$image_name);
			$this->session->set_flashdata('success','The image is successfully deleted.');
		} else {
			$this->session->set_flashdata('error','Database error occured.Please contact your system administrator.');
		}
		redirect(site_url('mixes/edit/'.$feed_id));
		
	}
	*/
}
?>