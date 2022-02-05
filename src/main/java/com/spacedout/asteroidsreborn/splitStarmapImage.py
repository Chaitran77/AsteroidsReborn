import os

# to split the 16384x8192 starmap image into 2048x2048 sections, I wrote the following script which uses ImageMagick's convert command
# also had to change the lines in /etc/ImageMagick-6/policy.xml to the following to allow this to work:
#  <policy domain="resource" name="memory" value="1024MiB"/>
#  <policy domain="resource" name="width" value="50KP"/>
#  <policy domain="resource" name="height" value="50KP"/>

os.mkdir("starmap-sections")
os.chdir("starmap-sections")

for x in range(0, 8):
	for y in range(0, 4):
		os.system("convert -extract 2048x2048+{0}+{1} ../starmap_2020_16k_gal.png starmap-section-{2}{3}.png".format(x*2048, y*2048, x, y))
