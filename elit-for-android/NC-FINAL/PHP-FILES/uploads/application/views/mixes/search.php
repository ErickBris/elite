
			<ul class="breadcrumb">
				<li><a href="<?php echo site_url();?>">Panel</a> <span class="divider"></span></li>
				<li><a href="<?php echo site_url('mixes');?>">Mix list</a> <span class="divider"></span></li>
				<li>Search Result</li>
			</ul>
			
			<div class='row'>
				<div class='col-sm-9'>
					<?php
					$attributes = array('class' => 'form-inline');
					echo form_open(site_url('mixes/search'), $attributes);
					?>
						<div class="form-group">
					   	<input type="text" name="searchterm" class="form-control" placeholder="Search"
					   		value="<?php echo $searchterm;?>">
					  	</div>
					  	<button type="submit" class="btn btn-default">Search</button>
					  	<a href='<?php echo site_url('mixes');?>' class="btn btn-default">Reset</a>
					</form>
				</div>	
				<div class='col-sm-3'>
					<a href='<?php echo site_url('mixes/add');?>' class='btn btn-primary pull-right'><span class='glyphicon glyphicon-plus'></span> Add MiX</a>
				</div>
			</div>
			
			<br/>
			
			<!-- Message -->
			<?php if($this->session->flashdata('success')): ?>
				<div class="alert alert-success fade in">
					<?php echo $this->session->flashdata('success');?>
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
				</div>
			<?php elseif($this->session->flashdata('error')):?>
				<div class="alert alert-danger fade in">
					<?php echo $this->session->flashdata('error');?>
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
				</div>
			<?php endif;?>
			
			<table class="table table-striped table-bordered">
				<tr>
					<th>No</th>
					<th>Title</th>
					
					<?php if(in_array('edit',$allowed_accesses)):?>
					<th>Edit</th>
					<?php endif;?>
					
					<?php if(in_array('delete',$allowed_accesses)):?>
					<th>Delete</th>
					<?php endif;?>
					
					<?php if(in_array('publish',$allowed_accesses)):?>
					<th>Publish</th>
					<?php endif;?>
					
				</tr>
				<?php
					if(!$count=$this->uri->segment(3))
						$count = 0;
					if(isset($mixfeed) && count($mixfeed->result())>0):
						foreach($mixfeed->result() as $mixfeed):					
				?>
						<tr>
							<td><?php echo ++$count;?></td>
							<td><?php echo $mixfeed->title;?></td>
							
							<?php if(in_array('edit',$allowed_accesses)):?>
							<td><a href='<?php echo site_url("mixes/edit/".$mixfeed->id);?>'><i class='glyphicon glyphicon-edit'></i></a></td>
							<?php endif;?>
							
							
							<?php if(in_array('delete',$allowed_accesses)):?>
							<td><a href='<?php echo site_url("mixes/delete/".$mixfeed->id);?>'><i class='glyphicon glyphicon-trash'></i></a></td>
							<?php endif;?>
							
							<?php if(in_array('publish',$allowed_accesses)):?>
							<td>
								<?php if($mixfeed->status == 1):?>
								
									<button class="btn btn-sm btn-primary publish"   
										feedId='<?php echo $mixfeed->id;?>'>Yes
									</button>
									
								<?php else:?>
								
									<button class="btn btn-sm btn-danger unpublish"
									feedId='<?php echo $mixfeed->id;?>'>No</button>
								
								<?php endif;?>
							</td>
							<?php endif;?>
						</tr>
						<?php
						endforeach;
					else:
				?>
						<tr>
							<td colspan='7'>There is no data for feed.</td>
						</tr>
				<?php
					endif;
				?>
			</table>
			
			<?php 
				$this->pagination->initialize($pag);
				echo $this->pagination->create_links();
			?>
			
			<script>
			$(document).ready(function(){
				$(document).delegate('.publish','click',function(){
					
					var btn = $(this);
					var id = $(this).attr('feedId');
					
					$.ajax({
						url: '<?php echo site_url('mixes/publish');?>/'+id,
						method:'GET',
						success:function(msg){
							if(msg == 'true')
								btn.addClass('unpublish').addClass('btn-primary')
									.removeClass('publish').removeClass('btn-danger')
									.html('Yes');
							else
								alert('System error occured. Please contact your system administrator.');
						}
					});
				});
				
				$(document).delegate('.unpublish','click',function(){
					
					var btn = $(this);
					var id = $(this).attr('feedId');
					
					$.ajax({
						url: '<?php echo site_url('mixes/unpublish');?>/'+id,
						method:'GET',
						success:function(msg){
							if(msg == 'true')
								btn.addClass('publish').addClass('btn-danger')
									.removeClass('unpublish').removeClass('btn-primary')
									.html('No');
							else
								alert('System error occured. Please contact your system administrator.');
						}
					});
				});
			});
			</script>
