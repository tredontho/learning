#[derive(Debug, Clone)]
pub struct RGB(pub usize, pub usize, pub usize);

#[derive(Debug)]
pub struct PPM {
    width: usize,
    height: usize,
    data: Vec<RGB>,
}

impl PPM {
    pub fn new(width: usize, height: usize, data: Vec<RGB>) -> PPM {
        assert_eq!(data.len(), width * height);
        PPM {
            width,
            height,
            data,
        }
    }

    pub fn gradient(width: usize, height: usize) -> PPM {
        let mut data: Vec<RGB> = Vec::with_capacity(width * height);
        for j in 0..height {
            for i in 0..width {
                let r = (255.0 * i as f32 / (width - 1) as f32) as usize;
                let g = (255.0 * j as f32 / (height - 1) as f32) as usize;
                let b = 0;
                data.push(RGB(r, g, b));
            }
        }
        PPM {
            width,
            height,
            data,
        }
    }

    pub fn to_string(&self) -> String {
        let mut buffer = String::new();
        buffer.push_str("P3\n");
        buffer.push_str(&format!("{} {}\n", self.width, self.height));
        buffer.push_str("255\n");
        // TODO: can probably factor this out
        for pixel in self.data.iter() {
            buffer.push_str(&format!("{} {} {}\n", pixel.0, pixel.1, pixel.2));
        }
        buffer
    }
}
