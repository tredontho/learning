{
  description = "Flake for workshop-runner, a CLI to drive test-driven workshops written in Rust.";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
    utils.url = "github:numtide/flake-utils";
    naersk.url = "github:nix-community/naersk";
    workshop-runner = {
      url = "github:mainmatter/rust-workshop-runner";
      flake = false;
    };
  };

  outputs = { self, nixpkgs, utils, naersk, workshop-runner }:
  utils.lib.eachDefaultSystem (system:
  let
    pkgs = nixpkgs.legacyPackages.${system};
    naersk' = pkgs.callPackage naersk {};
  in
    {
      packages.default = naersk'.buildPackage {
        src = workshop-runner;
      };
    }
  );
}
