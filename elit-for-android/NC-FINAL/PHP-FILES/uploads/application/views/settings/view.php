
			<ul class="breadcrumb">
				<li><a href="<?php echo site_url();?>">Panel</a> <span class="divider"></span></li>
				<li>Setting</li>
			</ul>
			
		
			
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
					<th>Logo</th>
					<th>Name</th>
					<th>Color NavBar</th>
					<th>Color View Back</th>
					<th>Color NavBar Shadow Color</th>
					<th>Menu Color</th>
					<th>Site Url</th>
					
					
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
						<tr class="active">
							<td>
								
								
								<?php
							$images = $this->image->get_all_by_type($feed->id, 'setting')->result();
													        ?>
						        
						        <?php
								$i= 0;
								foreach ($images as $img) {
									if ($i>0 && $i%3==0) {
										echo "</div>";
									}
									
									echo '<div class="col-md-3" style="height:100; width:100"><div>'.
										'<img src=" '.base_url('uploads/thumbs/'.$img->path).'"  class="img-rounded" "><br/>'.
										
										'</div>';
								   
								}
							?>
								
								
							</td>
							<td><?php 
							echo $rest = substr($feed->name, 0, 250).'...';
							// $rest = substr("abcdef", 0, 8);
								
							?>
							</td>
							<td><?php 
							echo $rest = substr($feed->cnavbar, 0, 250).'...'; //$feed->description;
							?>
							</td>
							<td><?php 
							echo $rest = substr($feed->cview, 0, 250).'...'; //$feed->description;
							?>
							</td>
							<td><?php 
							echo $rest = substr($feed->cnavbarshadow, 0, 250).'...'; //$feed->description;
							?>
							</td>
							<td><?php 
							echo $rest = substr($feed->menucolor, 0, 250).'...'; //$feed->description;
							?>
							</td>
							<td><?php 
							echo $rest = substr($feed->site, 0, 250).'...'; //$feed->description;
							?>
							</td>
							
							
							<?php if(in_array('edit',$allowed_accesses)):?>
							<td><a href='<?php echo site_url("settings/edit/".$feed->id);?>'><i class='glyphicon glyphicon-edit'></i></a></td>
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

			
