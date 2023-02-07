import clsx from "clsx";
import Link from "next/link";

type Props = {
  children: React.ReactNode;
  href: string;
  styleVisited?: boolean;
};

export default function TextLink({ children, href, styleVisited }: Props) {
  return (
    <Link
      href={href}
      className={clsx("underline text-blue-400 hover:text-blue-500", {
        "visited:text-purple-400": styleVisited,
      })}
    >
      {children}
    </Link>
  );
}
