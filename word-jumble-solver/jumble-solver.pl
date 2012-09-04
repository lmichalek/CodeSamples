#!/usr/bin/perl 

# Variables for reading in dictionary:
my $file_contents;
my @dictionary;

# Variables for hashing dictionary:
my $word;
my $sortedword;
my %hash;

# Variables for solving the given jumble:
my @jumbwords;
my $jumble;
my $sortedjumble;

open FILE, "dictionary.txt" or die "Couldn't open dictionary file!";
$file_contents = do { local $/; <FILE> };
@dictionary = split(/\n/, $file_contents);
close FILE;

foreach$word(@dictionary)
{
	$sortedword = join('', sort split("",$word));
	if(exists($hash{$sortedword})) 
	{
		$hash{$sortedword} .= " " . $word;
		$hash{$sortedword} = join ' ', sort split(" ", $hash{$sortedword} );
	}
	else
	{
		$hash{$sortedword} = $word;
	}
}

@jumbwords = @ARGV;

foreach$jumble(@jumbwords)
{
	$sortedjumble = join('', sort split("",$jumble));
	if(exists($hash{$sortedjumble}))
	{
		print "$jumble: " . $hash{$sortedjumble} . "\n";
	}
	else
	{
		print "$jumble: \n";
	}
}
