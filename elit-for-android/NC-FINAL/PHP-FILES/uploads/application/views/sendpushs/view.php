
			<ul class="breadcrumb">
				<li><a href="<?php echo site_url();?>">Panel</a> <span class="divider"></span></li>
				<li>Push</li>
			</ul>
			
			<div class='row'>
				<div class='col-sm-12'>
					<a href='<?php echo site_url('sendpushs/add');?>' class='btn btn-success pull-right'><span class='glyphicon glyphicon-plus'></span> Send push</a>
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
					<th>Message</th>
				<!--	<th>News</th> -->
					
					<!--
					<?php if(in_array('edit',$allowed_accesses)):?>
					<th>Edit</th>
					<?php endif;?> -->
					
					<?php if(in_array('delete',$allowed_accesses)):?>
					<th>Delete</th>
					<?php endif;?>
					
									
				</tr>
				<?php
					if(!$count=$this->uri->segment(3))
						$count = 0;
					if(isset($feeds) && count($feeds->result())>0):
						foreach($feeds->result() as $feed):					
				?>
						<tr>
							<td><?php echo ++$count;?></td>
							<td><?php 
							echo $rest = substr($feed->title, 0, 50).'...';
															
							?>
							</td>
							<!--<td><?php 
							echo $rest = substr($feed->description, 0, 50).'...'; 
							?>
							</td>-->
							
							
							<!--<?php if(in_array('edit',$allowed_accesses)):?>
							<td><a href='<?php echo site_url("newsies/edit/".$feed->id);?>'><i class='glyphicon glyphicon-eye-open'></i></a></td>
							<?php endif;?> -->
							
							
							<?php if(in_array('delete',$allowed_accesses)):?>
							<td><a href='<?php echo site_url("sendpushs/delete/".$feed->id);?>'><i class='glyphicon glyphicon-remove'></i></a></td>
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
