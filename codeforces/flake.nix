{
  description = "Flake for haskell development";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system:
      let pkgs = nixpkgs.legacyPackages.${system};
          haskellPackages = pkgs.haskell.packages.ghc810;
      in {
        devShell = pkgs.mkShell {
          buildInputs = [
            haskellPackages.cabal-install
            haskellPackages.ghcid
            haskellPackages.ghc
            haskellPackages.haskell-language-server
            pkgs.haskellPackages.fourmolu # Trying to use the version of fourmolu from haskellPackages fails because it needs cabal version 3.6
          ];
        };
      });
}
