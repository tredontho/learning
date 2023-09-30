{
  description = "Haskell in Depth";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs";
  };

  outputs = { self, nixpkgs }:
    let
      allSystems = [
        "x86_64-linux" # 64-bit Intel/AMD Linux (and WSL on Windows)
        "aarch64-darwin" # 64-bit ARM macOS
      ];

      forAllSystems = f: nixpkgs.lib.genAttrs allSystems (system: f {
        pkgs = import nixpkgs { inherit system; };
      });
    in
    {
      devShells = forAllSystems ({ pkgs }: {
        default = pkgs.mkShell {
          packages = with pkgs; with haskellPackages; [
            haskell.compiler.ghc810
            cabal-install
            (haskell-language-server.override { ghc = haskell.compiler.ghc810; })
            fourmolu
          ];

          buildInputs = [
            pkgs.zlib
          ];
        };
      });
    };
}
