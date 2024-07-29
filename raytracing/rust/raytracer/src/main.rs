use raytracer::RGB;
fn main() {
    let width = 640;
    let height = 480;

    eprintln!("Maximum width/height is {}", usize::max_value());
    // let image = raytracer::PPM::new(width, height, vec![RGB(0,0,0);width*height]);
    let image = raytracer::PPM::gradient(width, height);
    println!("{}", image.to_string());
}
