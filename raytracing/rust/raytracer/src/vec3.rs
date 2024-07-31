use std::ops::{Add, AddAssign, Div, DivAssign, Mul, MulAssign, Neg};

#[derive(Debug, PartialEq, Clone)]
pub struct Vec3 {
    x: f64,
    y: f64,
    z: f64,
}

pub const I: Vec3 = Vec3 {
    x: 1.0,
    y: 0.0,
    z: 0.0,
};
pub const J: Vec3 = Vec3 {
    x: 0.0,
    y: 1.0,
    z: 0.0,
};
pub const K: Vec3 = Vec3 {
    x: 0.0,
    y: 0.0,
    z: 1.0,
};

impl Vec3 {
    pub fn new(x: f64, y: f64, z: f64) -> Vec3 {
        Self { x, y, z }
    }

    pub fn zero() -> Vec3 {
        Self::new(0.0, 0.0, 0.0)
    }

    pub fn unit() -> Vec3 {
        Self::new(1.0, 1.0, 1.0)
    }

    /// Calculates the vector that has magnitude 1 but retains the same directionality
    ///
    /// # Examples
    ///
    /// ```
    /// use raytracer::vec3::*;
    ///
    /// let v0 = Vec3::unit();
    /// let v1 = v0.normalize();
    /// assert_eq!(1.0, v1.length());
    /// ```
    pub fn normalize(&self) -> Vec3 {
        self / self.length()
    }

    pub fn get_x(&self) -> f64 {
        self.x
    }
    pub fn get_y(&self) -> f64 {
        self.y
    }
    pub fn get_z(&self) -> f64 {
        self.z
    }
    pub fn length(&self) -> f64 {
        f64::sqrt(self.length_squared())
    }
    pub fn length_squared(&self) -> f64 {
        self.x * self.x + self.y * self.y + self.z * self.z
    }

    /// Dot product of two vectors
    ///
    /// # Examples
    ///
    /// ```
    /// use raytracer::vec3;
    ///
    /// assert_eq!(0.0, vec3::I.dot(&vec3::J));
    /// ```
    ///
    pub fn dot(&self, rhs: &Self) -> f64 {
        self.x * rhs.x + self.y * rhs.y + self.z * rhs.z
    }
}

impl Add<&Vec3> for &Vec3 {
    type Output = Vec3;

    fn add(self, rhs: &Vec3) -> Self::Output {
        Vec3 {
            x: self.x + rhs.x,
            y: self.y + rhs.y,
            z: self.z + rhs.z,
        }
    }
}

impl AddAssign<&Vec3> for Vec3 {
    fn add_assign(&mut self, rhs: &Self) {
        self.x += rhs.x;
        self.y += rhs.y;
        self.z += rhs.z;
    }
}

impl Mul<f64> for &Vec3 {
    type Output = Vec3;

    fn mul(self, rhs: f64) -> Self::Output {
        Vec3 {
            x: self.x * rhs,
            y: self.y * rhs,
            z: self.z * rhs,
        }
    }
}

impl Mul<&Vec3> for f64 {
    type Output = Vec3;

    fn mul(self, rhs: &Vec3) -> Self::Output {
        rhs * self
    }
}

// Hadamard product
impl Mul<&Vec3> for &Vec3 {
    type Output = Vec3;

    /// Computes the Hadamard product of two vectors
    ///
    /// ## Examples
    ///
    /// ```
    /// use raytracer::vec3::Vec3;
    ///
    /// let v0 = Vec3::new(1.5, 2.0, 3.0);
    /// let v1 = Vec3::new(5.0, 10.0, 15.0);
    /// let expected = Vec3::new(7.5, 20.0, 45.0);
    ///
    /// assert_eq!(expected, &v0 * &v1);
    /// ```
    fn mul(self, rhs: &Vec3) -> Self::Output {
        Vec3 {
            x: self.x * rhs.x,
            y: self.y * rhs.y,
            z: self.z * rhs.z,
        }
    }
}

impl MulAssign<f64> for Vec3 {
    fn mul_assign(&mut self, rhs: f64) {
        self.x *= rhs;
        self.y *= rhs;
        self.z *= rhs;
    }
}

impl Div<f64> for &Vec3 {
    type Output = Vec3;

    fn div(self, rhs: f64) -> Self::Output {
        Vec3 {
            x: self.x / rhs,
            y: self.y / rhs,
            z: self.z / rhs,
        }
    }
}

impl DivAssign<f64> for Vec3 {
    fn div_assign(&mut self, rhs: f64) {
        self.x /= rhs;
        self.y /= rhs;
        self.z /= rhs;
    }
}

impl Neg for &Vec3 {
    type Output = Vec3;

    fn neg(self) -> Self::Output {
        Vec3 {
            x: -self.x,
            y: -self.y,
            z: -self.z,
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn length_squared() {
        let v0 = Vec3::zero();

        assert_eq!(0.0, v0.length_squared());

        let v0 = Vec3 {
            x: 1.0,
            y: 2.0,
            z: 3.0,
        };

        assert_eq!(14.0, v0.length_squared());

        let v1 = Vec3 {
            x: -1.0,
            y: -1.0,
            z: -1.0,
        };
        assert_eq!(3.0, v1.length_squared());
    }

    #[test]
    fn length() {
        let v0 = Vec3::zero();

        assert_eq!(0.0, v0.length());

        let v0 = Vec3 {
            x: 1.0,
            y: 2.0,
            z: 3.0,
        };

        assert_eq!(f64::sqrt(14.0), v0.length());

        let v1 = Vec3 {
            x: -1.0,
            y: -1.0,
            z: -1.0,
        };
        assert_eq!(f64::sqrt(3.0), v1.length());
    }

    #[test]
    fn add_assign() {
        let mut v0 = Vec3::zero();
        let mut v1 = Vec3 {
            x: 1.0,
            y: 2.0,
            z: 3.0,
        };
        let expected = Vec3 { ..v1 };
        v0 += &v1;
        assert_eq!(expected, v0);
        let expected = Vec3 {
            x: 2.0,
            y: 4.0,
            z: 6.0,
        };
        v1 += &v1.clone();
        assert_eq!(expected, v1);
    }

    #[test]
    fn scale() {
        let v0 = Vec3 {
            x: 1.0,
            y: 2.0,
            z: 3.0,
        };
        let expected = Vec3 {
            x: 2.0,
            y: 4.0,
            z: 6.0,
        };

        assert_eq!(expected, &v0 * 2.0);
        assert_eq!(expected, 2.0 * &v0);
    }

    #[test]
    fn scale_assign() {
        let mut v0 = Vec3 {
            x: 1.0,
            y: 2.0,
            z: 3.0,
        };
        let scalar = 10.0;
        let expected = Vec3 {
            x: 10.0,
            y: 20.0,
            z: 30.0,
        };
        v0 *= scalar;
        assert_eq!(expected, v0);
    }

    #[test]
    fn inverted_scale() {
        let v0 = Vec3 {
            x: 1.0,
            y: 2.0,
            z: 3.0,
        };
        let scalar = 10.0;
        let expected = Vec3 {
            x: 0.10,
            y: 0.20,
            z: 0.30,
        };
        assert_eq!(expected, &v0 / scalar);
    }

    #[test]
    fn inverted_scale_assign() {
        let mut v0 = Vec3 {
            x: 1.0,
            y: 2.0,
            z: 3.0,
        };
        let scalar = 10.0;
        let expected = Vec3 {
            x: 0.10,
            y: 0.20,
            z: 0.30,
        };
        v0 /= scalar;
        assert_eq!(expected, v0);
    }

    #[test]
    fn negate() {
        let v0 = Vec3 {
            x: 1.0,
            y: 2.0,
            z: 3.0,
        };
        let expected = Vec3 {
            x: -1.0,
            y: -2.0,
            z: -3.0,
        };

        assert_eq!(expected, -&v0);
        assert_eq!(v0, -&(-&v0));
    }
}
