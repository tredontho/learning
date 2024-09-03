use std::cmp::max;
use std::process::exit;

use raytracer::Vec3;
use raytracer::PPM;
use raytracer::RGB;

fn main() {
    let image_width: usize = 400;
    let aspect_ratio: f64 = 16.0 / 9.0;
    let image_height: usize = max(1, (image_width as f64 / (aspect_ratio)) as usize);

    let viewport_height = 2.0;
    let viewport_width = viewport_height * (image_height as f64 / image_width as f64);

    let image = PPM::gradient(image_width, image_height);
    println!("{}", image.to_string());
}
