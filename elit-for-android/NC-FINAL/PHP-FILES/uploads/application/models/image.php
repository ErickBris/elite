<?php 
class Image extends Club_Model
{
	protected $table_name;

	function __construct()
	{
		parent::__construct();
		$this->table_name = '76_images';
	}

	function save(&$data)
	{
		if ($this->db->insert($this->table_name,$data)) {
			$data['id'] = $this->db->insert_id();
			return true;
		}
		return false;
	}

	function get_all($limit=false,$offset=false)
	{
		$this->db->from($this->table_name);
		if ($limit) {
			$this->db->limit($limit);
		}
		
		if ($offset) {
			$this->db->offset($offset);
		}
		
		return $this->db->get();
	}

	function get_all_by_item($item_id, $limit=false, $offset=false)
	{
		$this->db->from($this->table_name);
		$this->db->where('item_id',$item_id);
		if ($limit) {
			$this->db->limit($limit);
		}
		
		if ($offset) {
			$this->db->offset($offset);
		}
		
		return $this->db->get();
	}

	function get_info($id)
	{
		$query = $this->db->get_where($this->table_name,array('id'=>$id));
		
		if ($query->num_rows()==1) {
			return $query->row();
		} else {
			return $this->get_empty_object($this->table_name);
		}
	}

	function get_multiple_info($ids)
	{
		$this->db->from($this->table_name);
		$this->db->where_in($ids);
		return $this->db->get();
	}

	function count_all()
	{
		$this->db->from($this->table_name);
		return $this->db->count_all_results();
	}

	function count_all_by_item($item_id)
	{
		$this->db->from($this->table_name);
		$this->db->where('item_id',$item_id);
		return $this->db->count_all_results();
	}

	function delete($id)
	{
		$this->db->where('id',$id);
		return $this->db->delete($this->table_name);
 	}
 	
 	function get_all_by_type($parent_id, $type, $limit=false, $offset=false)
 	{
 		$this->db->from($this->table_name);
 		$this->db->where('item_id',$parent_id);
 		$this->db->where('type', $type);
 		
 		if ($limit) {
 			$this->db->limit($limit);
 		}
 		
 		if ($offset) {
 			$this->db->offset($offset);
 		}
 		
 		return $this->db->get();
 	}
}
?>