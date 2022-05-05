
			<ul class="breadcrumb">
				<li><a href="<?php echo site_url();?>">Panel</a> <span class="divider"></span></li>
				<li>About</li>
			</ul>
			
		<!--	<div class='row'>
				<div class='col-sm-9'>
					<?php
					$attributes = array('class' => 'form-inline');
					echo form_open(site_url('abouts/search'), $attributes);
					?>
						<div class="form-group">
					   	<input type="text" name="searchterm" class="form-control" placeholder="Search">
					  	</div>
					  	<button type="submit" class="btn btn-default">Search</button>
					</form>
				</div>	
				<div class='col-sm-3'>
					<a href='<?php echo site_url('abouts/add');?>' class='btn btn-primary pull-right'><span class='glyphicon glyphicon-plus'></span> Add News</a>
				</div>
			</div>-->
			
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
			
			<table class="table  table-bordered">
				<tr class="active">
					<th>Club</th>
					<th>About</th>
					<th>Adress</th>
					<th>Phone</th>
					
					
					<?php if(in_array('edit',$allowed_accesses)):?>
					<th>Edit</th>
					<?php endif;?>
					
									
					
				</tr>
				<?php
					if(!$count=$this->uri->segment(3))
						$count = 0;
					if(isset($aboutsdata) && count($aboutsdata->result())>0):
						foreach($aboutsdata->result() as $feed):					
				?>
						<tr class="success">
							<!--<td><?php echo ++$count;?></td>-->
							<td><?php 
							echo $rest = substr($feed->name, 0, 50).'...';
							// $rest = substr("abcdef", 0, 8);
								
							?>
							</td>
							<td><?php 
							echo $rest = substr($feed->description, 0, 50).'...'; //$feed->description;
							?>
							</td>
							<td><?php 
							echo $rest = substr($feed->adres, 0, 50).'...'; //$feed->description;
							?>
							</td>
							<td><?php 
							echo $rest = substr($feed->phone, 0, 50).'...'; //$feed->description;
							?>
							</td>
							
							
							<?php if(in_array('edit',$allowed_accesses)):?>
							<td><a href='<?php echo site_url("abouts/edit/".$feed->id);?>'><i class='glyphicon glyphicon-edit'></i></a></td>
							<?php endif;?>
							
							
							<!--<?php if(in_array('delete',$allowed_accesses)):?>
							<td><a href='<?php echo site_url("abouts/delete/".$feed->id);?>'><i class='glyphicon glyphicon-trash'></i></a></td>
							<?php endif;?>-->
							
							
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

			
