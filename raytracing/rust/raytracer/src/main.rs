use raytracer::RGB;
fn main() {
    let width = 256;
    let height = 256;

    let image = raytracer::PPM::gradient(width, height);
    println!("{}", image.to_string());
}
