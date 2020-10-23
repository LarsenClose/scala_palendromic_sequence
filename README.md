# Palindrome Finder of Sequences Which Sum to (n)

- Authors: Stuart Griffin and Larsen Close

## Started on spark implementatiopn ./spark/src/main/scala

### Important Functions

#### 1. find_combinations_sum_n(start: Int, end: Int, results[], index)

> Recursive function which returns one instance of each sequence of all lengths which sum to N

#### 2. permute_and_palendrome(start: Int, end: Int, results[], index)

> Iterative function which filters out all combinations which do not contain user input 'm'
> Then eliminates all combinations which are even in length and contain and element with an odd number of instances
> and eliminates all combinations which are odd in length and have an element with an even number of instances
> Then after combinations screened permutations is called on them and they are check for being palendromes

### 3. isPalindrome(permutedSums: ArrayBuffer[Int]): Boolean

> Iterative function checking if the permutations of sequences for whether they are palindromes
> Checks from head-> 1/4 for equals 3/4 < -tails and head1/4 -> 1/2 for equals 1/2 <-3/4

#### To Note

> Debug variable set default = false, change in file to true much migher verbosity but lower performanec
> UI is exactly to specifications within the assignemt
> Speed has been inconsistent on the lower numbers it has been performing much faster than times.csv but meets
> and then passes around the 30-somethines(n) has still varied a lot hower. The two machines we've tried it on
> are both pretty powerful

``` shell
            .-/+oossssoo+/-.               lclose@synthetic 
        `:+ssssssssssssssssss+:`           ---------------- 
      -+ssssssssssssssssssyyssss+-         OS: Ubuntu 20.04.1 LTS x86_64 
    .ossssssssssssssssssdMMMNysssso.       Host: N7 Z390 1.0 
   /ssssssssssshdmmNNmmyNMMMMhssssss/      Kernel: 5.4.0+ 
  +ssssssssshmydMMMMMMMNddddyssssssss+     Uptime: 18 hours, 47 mins 
 /sssssssshNMMMyhhyyyyhmNMMMNhssssssss/    Packages: 3692 (dpkg), 13 (snap) 
.ssssssssdMMMNhsssssssssshNMMMdssssssss.   Shell: zsh 5.8 
+sssshhhyNMMNyssssssssssssyNMMMysssssss+   Resolution: 1920x1080, 3440x1440 
ossyNMMMNyMMhsssssssssssssshmmmhssssssso   DE: GNOME 
ossyNMMMNyMMhsssssssssssssshmmmhssssssso   WM: Mutter 
+sssshhhyNMMNyssssssssssssyNMMMysssssss+   WM Theme: Adwaita 
.ssssssssdMMMNhsssssssssshNMMMdssssssss.   Theme: Yaru-dark [GTK2/3] 
 /sssssssshNMMMyhhyyyyhdNMMMNhssssssss/    Icons: Yaru [GTK2/3] 
  +sssssssssdmydMMMMMMMMddddyssssssss+     Terminal: guake 
   /ssssssssssshdmNNNNmyNMMMMhssssss/      CPU: Intel i9-9900K (16) @ 5.000GHz 
    .ossssssssssssssssssdMMMNysssso.       GPU: NVIDIA GeForce RTX 2080 Ti 
      -+sssssssssssssssssyyyssss+-         Memory: 4077MiB / 64249MiB 
        `:+ssssssssssssssssss+:`
            .-/+oossssoo+/-.                                       
                                                                   
---

Then on:
2.4 GHz 8-Core Intel Core i9
32 GB 2400 MHz DDR4

```

##### Attempted optimizations

 1. Various data structures sepecifically also using entirely bytes. It actually seemed to lower the performance of the program I think because it ended up leading to many more conversons
 2. Increasing the 'functionality' to allow greater parallelization
 3. Running through spark-shell to force execution across multiple cpus.I have a Raspberry Pi cluster running kubernetes and was trying to get it running up on there, just stuck on syntactically where exactly we can add the sparkcontext.parallelization. Struggles to come up with a thread safer permutations method and so we're basically having to let it go for not until somemore instight into the spark code and then can load it right onto 9 raspberry pi 4's see what's feasible. With the pip.itertools there's native thread safe implementations for doing it all on spark as well which looks really cool.