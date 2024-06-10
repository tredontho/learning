{
  description = "Flake for working through the Rust Book";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
    utils.url = "github:numtide/flake-utils";
    workshop-runner = {
      url = "./workshop-runner";
      inputs.nixpkgs.follows = "nixpkgs";
    };
  };

  outputs = { self, nixpkgs, utils, workshop-runner}: 
    utils.lib.eachDefaultSystem (system:
      let pkgs = nixpkgs.legacyPackages.${system}; in
      {
        devShells.default = pkgs.mkShell {
          buildInputs = with pkgs; [
            cargo
            rust-analyzer
            rustc
            rustfmt
            workshop-runner.packages.${system}.default
          ];
        };
      });
}
