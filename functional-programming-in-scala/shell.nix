let
	sources = import ./nix/sources.nix { };
in
  {pkgs ? import sources.nixpkgs {}, jdk ? "jdk11"}:
  pkgs.mkShell {
    buildInputs = [
      pkgs.sbt
      pkgs.${jdk}
      pkgs.nixfmt
    ];
  }

