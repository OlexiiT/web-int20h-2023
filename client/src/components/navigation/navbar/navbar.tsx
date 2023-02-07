import Link from "next/link";
import { useState } from "react";
import styles from "./styles.module.scss";
import clsx from "clsx";
import SearchBar from "@/components/forms/inputs/search-bar";

const basicRoutes = [
  {
    href: "/recipes",
    label: "Recipes",
    key: "recipes",
  },
  {
    href: "/products",
    label: "Products",
    key: "products",
  },
];

export default function Navbar() {
  const [isOpen, setIsOpen] = useState(false);
  return (
    <nav className="w-full bg-gray-800 shadow">
      <div className="justify-start px-4 mx-auto lg:max-w-7xl md:items-stretch md:flex md:px-8">
        <div>
          <div className="flex items-center justify-between py-3 md:py-5 md:block">
            <Link href="/">
              <h2 className="text-2xl text-white font-bold">Nyam</h2>
            </Link>
            <div className="md:hidden">
              <button
                className="p-2 text-gray-700 rounded-md outline-none focus:border-gray-400 focus:border"
                onClick={() => setIsOpen(!isOpen)}
              >
                {isOpen ? (
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="w-6 h-6 text-white"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                  >
                    <path
                      fillRule="evenodd"
                      d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                      clipRule="evenodd"
                    />
                  </svg>
                ) : (
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="w-6 h-6 text-white"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                    strokeWidth={2}
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      d="M4 6h16M4 12h16M4 18h16"
                    />
                  </svg>
                )}
              </button>
            </div>
          </div>
        </div>
        <div className="flex items-stretch flex-grow">
          <div
            className={`flex-1 pb-4 mt-8 block md:flex justify-center md:pb-0 space-y-4 md:space-y-0 md:mt-0 ${
              isOpen ? "block" : "hidden"
            }`}
          >
            <ul className="md:flex-grow justify-center space-y-4 md:flex md:space-x-6 md:space-y-0">
              <div className="md:flex md:flex-grow justify-center space-y-4 md:space-x-6 md:space-y-0">
                {basicRoutes.map((route) => (
                  <NavbarLink {...route} />
                ))}
              </div>
              <SearchBar placeholder="Ratatouille" outerContainerClassName="self-center" />
              <div className="flex items-stretch">
                <NavbarLink href="/login" label="Login" key="login" />
              </div>
            </ul>
          </div>
        </div>
      </div>
    </nav>
  );
}

type NavbarLinkProps = {
  href: string;
  label: string;
};

function NavbarLink({ href, label }: NavbarLinkProps) {
  return (
    <Link
      href={href}
      className={clsx(
        "hover:text-blue-800 flex items-center",
        styles["nav-item"]
      )}
    >
      <li className="text-white">{label}</li>
    </Link>
  );
}
