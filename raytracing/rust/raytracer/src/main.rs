use std::cmp::max;
use std::process::exit;

use raytracer::Vec3;
use raytracer::PPM;
use raytracer::RGB;

fn main() {
    let aspect_ratio: f64 = 16.0 / 9.0;
    let image_width: usize = 400;

    // calculate image height based on image_width and aspect ratio, and ensure it's at least 1
    let image_height: usize = max(1, (image_width as f64 / (aspect_ratio)) as usize);

    // Camera
    let focal_length: f64 = 1.0;
    let viewport_height: f64 = 2.0;
    let viewport_width: f64 = viewport_height * (image_width as f64 / image_height as f64);
    let camera_center = Vec3::zero();

    // Vectors across the horizontal and down the veritcal viewport edges
    let viewport_u = Vec3::new(viewport_width, 0.0, 0.0);
    let viewport_v = Vec3::new(0.0, -viewport_height, 0.0);

    // Calculate the horizontal and vertical delta vecotrs from pixel to pixel
    let pixel_delta_u = &viewport_u / (image_width as f64);
    let pixel_delta_v = &viewport_v / (image_height as f64);

    // Calculate the location of the upper left pixel
    let viewport_upper_left = &camera_center - &Vec3::new(0.0, 0.0, focal_length) - &viewport_u / 2.0 - &viewport_v / 2.0;


    let image = PPM::gradient(image_width, image_height);
    println!("{}", image.to_string());
}
