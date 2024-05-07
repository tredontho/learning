{
  description = "A PureScript development environment";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
    easy-purescript.url = "github:justinwoo/easy-purescript-nix";
  };

  outputs = { self, nixpkgs, flake-utils, easy-purescript, ... }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs { inherit system; };
      in
      {
        devShells.default = easy-purescript.devShells.${system}.deluxe;
        # devShell = pkgs.mkShell {
        #   buildInputs = with pkgs; [
        #     purescript
        #     spago
        #     nodejs
        #     yarn
        #   ];

        #   shellHook = ''
        #     echo "Welcome to your PureScript development environment!"
        #   '';
        # };
      }
    );
}

